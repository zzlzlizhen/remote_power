$(function () {
    var projectId = $('#projectId').val();
    $("#jqGrid").jqGrid({
        url: baseURL + 'dev/list',
        datatype: "json",
        postData:{'projectId':projectId},
        colModel: [
            { label: '设备id', name: 'deviceId', index: "device_id", width: '0',hidden:true, key: true },
            { label: '设备编号', name: 'deviceCode', width: '10%',  editable: true,  //允许编辑
                editrules: {
                    edithidden:true, //即使该列隐藏也可编辑
                    required:true,   //该列值不得为空
                    custom:true,     //自定义校验规则
                    custom_func:function(value, colNames){ //自定义校验函数实现
                        var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
                            regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;
                        if(regEn.test(value) || regCn.test(value)){
                            return [false, "不能包含特殊字符"]; // 这里表示，当返回false时弹出的警告内容
                        }else{
                            return [true,""]; //返回true则不弹出任何内容
                        }
                    }
                },sorttype: "string",align:"center"},
            { label: '设备名称', name: 'deviceName', width: '10%' },
            { label: '所属分组', name: 'groupId', width: '10%' },
            { label: '光电池状态', name: 'photocellState', width: '10%' },
            { label: '蓄电池状态', name: 'batteryState', width: '10%' },
            { label: '负载状态', name: 'loadState', width: '10%' },
            { label: '信号状态', name: 'signalState', width: '10%' },
            { label: '亮度', name: 'light', width: '10%' },
            { label: '无线模块', name: 'communicationType', width: '10%' },
            {label: '操作',name:"action", width:'20%',align:'center',sortable:false,formatter:displayButtons}
        ],
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
            projectId:null,
            deviceCode: null,
            deviceName: null,
            groupId: null
        },
        showList: true,
        title:null,
        groupList:{},
        groups:[
            {
                groupId:"0",
                groupName:"默认分组"
            }
        ],
        types:[
            //通讯类型 1：2G 2：Lora； 3：NBLot
            {
                type:1,
                typeName: "2G",
            },
            {
                type:2,
                typeName: "Lora",
            },
            {
                type:3,
                typeName: "NBLot",
            }
        ],
        device :{
            deviceId:null,
            deviceCode:null,
            deviceName:null,
            groupId:null,
            projectId:null,
            deviceType:0,
            photocellState:0,
            batteryState:0,
            loadState:0,
            signalState:0,
            runStatus:0,
            light:null,
            communicationType:0,
            onOff:0,
            longitude:null,
            latitude:null,
            isDel:0,
            createUser:0,
            createName:null,
            createTime:null,
            updateUser:0,
            updateTime:null,
            batteryMrgin:null,
            batteryVoltage:0.0,
            photovoltaicCellVoltage:0.0,
            chargingCurrent:0.0,
            chargingPower:0.0,
            loadVoltage:0.0,
            loadPower:0.0,
            loadCurrent: 0.0,
            lightingDuration:0.0,
            morningHours:0.0,
            cityName:0.0,
            dischargeCapacitySum:0.0,
            chargingCapacitySum:0.0,
            version:0,
            deviceVersion:0
        }
    },
    methods: {
        created:function () {
            //如果没有这句代码，select中初始化会是空白的，默认选中就无s法实现
     /*       this.selectedGroup = this.groups[0].groupId;
            this.selectedType = this.types[0].type;*/

        },
        query: function () {
            vm.reload();
        },
        haveGroup: function () {
            vm.groups.groupId = $("#groupId").val();
        },
        getGroups: function (projectId) {
            $.get(baseURL + "/gro/groupList/"+projectId, function(r){
                vm.groups = r.groupList;
                console.log(JSON.stringify(vm.groups));
                for(i=0; i < vm.groups;i++){
                    vm.groups.groupId = vm.groupList[i].groupId;
                    vm.groups.groupName = vm.groupList[i].groupName;
                }
            });
        },
        groupType: function () {
            vm.device.communicationType = $("#groupType").val();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.device = {};
            vm.device.projectId = $('#projectId').val();
            vm.device.groupId = $("#groupId").val();
            vm.device.communicationType = $("#groupType").val();
            vm.getGroups( vm.device.projectId);
          /*  console.log(vm.device.projectId);*/
           /* alert(projectId);*/

        },
        update: function (event) {
            var deviceId = getSelectedRow();
            if(deviceId == null){
                return ;
            }
            vm.device.projectId = $('#projectId').val();
            vm.device.deviceId = deviceId;
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(deviceId);
            vm.getGroups( vm.device.projectId);
        },
        saveOrUpdate: function (event) {
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {

                var url = vm.device.deviceId == null ? "dev/add" : "dev/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    data:
                    "&deviceId=" + vm.device.deviceId +
                    "&projectId=" + vm.device.projectId +
                    "&deviceCode=" + vm.device.deviceCode +
                    "&deviceName=" + vm.device.deviceName +
                    "&groupId=" + vm.groups.groupId +
                    "&communicationType="+ vm.device.communicationType,
                    success: function(r){
                        if(r.code === 200){
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
            var deviceId = getSelectedRows();
            if(deviceId == null){
                return ;
            }
            var lock = false;
            confirm('确定要删除选中的记录？', function(){
                if(!lock) {
                    lock = true;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "dev/delete",
                        data:
                        "&deviceId=" + deviceId,
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
        getInfo: function(deviceId){
            $.get(baseURL + "dev/info/"+deviceId, function(r){
                vm.device = r.device;
            });
        },
        reload: function (event) {
            vm.q.projectId = $('projectId').val();
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'projectId':vm.q.projectId,'deviceCode':vm.q.deviceCode,'deviceName': vm.q.deviceName,'groupId':vm.q.groupId},
                page:page
            }).trigger("reloadGrid");
        }
    }
});