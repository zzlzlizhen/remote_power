$(function () {
    var projectId = $('#projectId').val();
    $("#jqGrid").jqGrid({
        url: baseURL + 'gro/list',
        datatype: "json",
        colModel: [
            { label: '组id', name: 'groupId', index: "group_id", width: '10%', key: true },
            { label: '组名称', name: 'groupName', width: '10%' },
            { label: '更新时间', name: 'updateTime', width: '10%' },
            {label: '操作',name:"action", width:'20%',align:'center',sortable:false,formatter:displayButtons}
        ],
        postData:{'projectId':projectId},
        viewrecords: true,/*显示记录*/
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: false,
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
            /*     var ids = jQuery("#jqGrid").jqGrid('getDataIDs');
                 for(var i=0;i < ids.length;i++){
                     var cl = ids[i];
                    /!* acc1 = "<input style='height:22px;width:60px;' type='button' id='queryBtn"+ cl +"'  value='查询' onclick=\"btnClick("+cl+",this)\"/>";*!/
                     acc1 = "<input style='height:22px;width:60px;' type='button' id=\"mapBtn\" class='mapBtn' @click=\"mapBtn\" value='地图'/>";
                     $("#jqGrid").jqGrid('setRowData',cl,{action:acc1});

                 }*/
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});
function displayButtons(cellvalue,options,rowObject) {
    var queryBtn = "<input style='margin-right: 8px;' class='btn btn-primary' type='button' value='查询' onclick=\"queryClick('"
        + options.rowId + "');\" />";
    var mapBtn = "<input class='btn btn-danger' type='button' value='地图' onclick=\"mapClick('"
        + options.rowId + "');\" />";
    /*        var queryBtn = "<input style='height:22px;width:60px;' type='button' id='queryBtn'  value='查询' onclick=\"queryClick()\"/>";
            var mapBtn = "<input style='height:22px;width:60px;' type='button' id='mapBtn'  value='地图' onclick=\"mapClick()\"/>";*/
    return queryBtn + mapBtn;
}

function queryClick(rowId) {
    alert(rowId);
}
function mapClick(rowId) {
    alert(rowId);
}
var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            groupId: null,
            groupName: null
        },
        showList: true,
        title:null,
        group:{
            groupId:null,
            groupName:null,
            projectId:null,
            deviceIds:null,
            createUser:0,
            createName:null,
            createTime:null,
            updateUser:0,
            updateTime:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";

            vm.group= {};
        },
        update: function (event) {
            var groupId = getSelectedRow();
            if(groupId == null){
                return ;
            }
            vm.group.groupId = groupId;
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(groupId);
        },
        saveOrUpdate: function (event) {
            var projectId = $('#projectId').val();
            alert(projectId);
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.group.groupId == null ? "gro/add" : "gro/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    data:
                    "&groupId=" + vm.group.groupId +
                    "&projectId=" + projectId +
                    "&groupName="+ vm.group.groupName,
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
            var groupId = getSelectedRows();
            if(groupId == null){
                return ;
            }
            var lock = false;
            confirm('确定要删除选中的记录？', function(){
                if(!lock) {
                    lock = true;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "gro/delete",
                        data:
                        "&groupId=" + groupId,
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
        getInfo: function(groupId){
            $.get(baseURL + "gro/info/"+groupId, function(r){
                vm.group= r.group;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'groupName':vm.q.groupName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});