$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pro/list',
        datatype: "json",
        colModel: [
            { label: '项目id', name: 'projectId', index: "project_id", width: '10%', key: true },
            { label: '项目编号', name: 'projectCode', width: '10%'},
            { label: '项目名称', name: 'projectName', width: '10%' },
            { label: '用户', name: 'createName', width: '10%' },
            { label: '总装机数量', name: 'sumCount', width: '10%' },
            { label: '网关数量', name: 'gatewayCount', width: '10%' },
            { label: '报警数量', name: 'callPoliceCount', width: '10%' },
            { label: '项目描述', name: 'projectDesc', width: '10%' },
            {label: '操作',name:"action", index:'action',width:'20%',align:'center',sortable:false}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            var ids = jQuery("#jqGrid").jqGrid('getDataIDs');
            for(var i=0;i < ids.length;i++){
                var cl = ids[i];
                acc1 = "<input style='height:22px;width:60px;' type='button' id='queryBtn"+ cl +"'  value='查询' @click=\"queryBtnClick("+cl+",this)\"  />"+
                 "<input style='height:22px;width:60px;' type='button' id='mapBtn"+ cl +"'  value='地图' @click=\"mapBtnClick("+cl+",this)\"  />";
                $("#jqGrid").jqGrid('setRowData',cl,{action:acc1});

            }
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});
//添加的 按钮的单击事件
function btnClick(cl,obj){
    var btnStr=$(obj).val();//传过来的button对象，调用的时候通过this关键词设置此参数
}
var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            projectCode: null,
            projectName: null,
            exclusiveUser: null
        },
        showList: true,
        title:null,
        project:{
            projectId:null,
            projectCode:null,
            projectName:null,
            projectDesc:null,
            cityId:null,
            exclusiveUser:0,
            runStatus:0,
            sumCount:0,
            gatewayCount:0,
            faultCount:0,
            callPoliceCount:0,
            projectStatus: 0
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.project = {};
        },
        update: function (event) {
            var projectId = getSelectedRow();
            if(projectId == null){
                return ;
            }
            vm.project.projectId = projectId;
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(projectId);
        },
        saveOrUpdate: function (event) {
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.project.projectId == null ? "pro/add" : "pro/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    data:
                    "&projectId=" + vm.project.projectId +
                    "&projectCode=" + vm.project.projectCode +
                    "&projectName=" + vm.project.projectName +
                    "&projectDesc=" + vm.project.projectDesc +
                    "&exclusiveUser="+ vm.project.exclusiveUser,
                    success: function(r){
                        if(r.code === 0){
                            layer.msg("操作成功", {icon: 1});
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
            });
        },
        del: function (event) {
            var projectId = getSelectedRows();
            if(projectId == null){
                return ;
            }
            var lock = false;
            confirm('确定要删除选中的记录？', function(){
                if(!lock) {
                    lock = true;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "pro/delete",
                        data:
                        "&projectId=" + projectId,
                        success: function(r){
                            if(r.code == 200){
                                alert('操作成功', function(){
                                    vm.reload();
                                });
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
                    });
                }
            }, function(){
            });
        },
        getInfo: function(projectId){
            $.get(baseURL + "pro/info/"+projectId, function(r){
                vm.project = r.project;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'projectCode':vm.q.projectCode,'projectName': vm.q.projectName,'exclusiveUser':vm.q.exclusiveUser},
                page:page
            }).trigger("reloadGrid");
        }
    }
});