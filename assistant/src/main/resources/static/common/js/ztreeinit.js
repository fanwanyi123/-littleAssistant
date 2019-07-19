var zTreeObj;
$(document).ready(function () {
    $("#selectTag").click(function () {
        $("#addTagModal").modal('show');
    })
    $.ajax({
        url: "getTreeData",
        type: "post",
        dataType: "json",
        enable: true,
        success: function (data) {
            initTreeIcon(data);
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);//实现加载树的方法
            zTreeObj.expandAll(true);
        }
    })
});
var setting = {
    edit: {
        enable: true
    },
    view: {
        selectedMulti: false, //设置是否能够同时选中多个节点
        showIcon: true,      //设置是否显示节点图标
        showLine: true,      //设置是否显示节点与节点之间的连线
        showTitle: true    //设置是否显示节点的title提示信息
    },
    check:{
        enable: true,        //设置是否显示checkbox复选框
        autoCheckTrigger: false,
        chkStyle: "checkbox",//值为checkbox或者radio表示
    },
    data: {
        simpleData: {
            enable: true,  //设置是否启用简单数据格式（zTree支持标准数据格式跟简单数据格式，上面例子中是标准数据格式）
            idKey: "id",
            pIdKey: "pid",
            rootPId: 0
        }
    },
    callback: {
        onClick: onClick ,           //定义节点单击事件回调函数
        onRightClick: OnRightClick,   //定义节点右键单击事件回调函数
        // beforeRename: beforeRename,   //定义节点重新编辑成功前回调函数，一般用于节点编辑时判断输入的节点名称是否合法
        // onDblClick: onDblClick,       //定义节点双击事件回调函数
        onCheck: onCheck              //定义节点复选框选中或取消选中事件的回调函数
    }
};

function onClick(event, treeId, treeNode, clickFlag) {
   alert(treeId);
}

function initTreeIcon(data) {
    for (var i=0;i<data.length;i++){
      if (data[i].pid ==0){
          data[i].icon = "common/css/zTreeStyle/img/diy/3.png"
      }else {
          data[i].icon = "common/css/zTreeStyle/img/diy/2.png"
      }
    }
}

function onCheck(event, treeId, treeNode) {
    if(treeNode.checked){    //注意，这里的树节点的checked状态表示勾选之后的状态
        zTreeObj.checkAllNodes(false);//取消所有节点的选中状态
        zTreeObj.checkNode(treeNode,true,true,false);
    }
}

function tagChecked() {
    var nodesChecked = zTreeObj.getSelectedNodes(true);
    $("#selectTag").text(nodesChecked);
}

function OnRightClick(event, treeId, treeNode) {
    checkDiv($(this))
    // showRMenu("firstNode", event.clientX, event.clientY+top);//处理位置，使用的是绝对位置
}
//显示被隐藏的DIV
function checkDiv(obj){
    //给要显示的DIV设置左边距
    var left = event.clientX;
    var intleft = parseInt(left) + 15;
    //给要显示的DIV设置上边距
    var top = event.clientY;
    var inttop = parseInt(top) +15;
    $("#rMenu").show();
    $("#rMenu").css({
        "background-color":'#33ee33',
        "position":'absolute',
        "z-index":1100,
        "top":inttop,
        "left":intleft
    });
}
//事件触发 隐藏菜单
function onBodyMouseDown(event) {
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({
            "visibility" : "hidden"
        });
    }
}