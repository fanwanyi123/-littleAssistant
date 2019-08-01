var vueObj;
$(document).ready(function (e) {
    vueFunction("#vueRecord", e);
});

function vueFunction(el, e) {
    vueObj = new Vue({
        el: el,
        data: {
            all: 1,
            pageInfo: [],
            pageNum: 5,
            currentPage: 1,
            recordId: 0,
            recordDetail: "",
            afterRecord: "",
            preRecord: "",
        },
        e: e,
        created: function () {
            this.getPageData();
        },
        methods: {
            btnClick(num) {
                if (num != this.currentPage) {
                    this.currentPage = num;
                }
            },
            handleSizeChange(val) {
                this.pageNum = val;//获取page-sizes里的每页显示几条数据的值，赋给我们自定义的每页显示数量的变量pageNum
                this.getPageData();//展示页面信息
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getPageData();//确定当前页面后刷新页面
            },
            getPageData() {
                console.log('现在是' + this.currentPage + '页');
                $.ajax({
                    url: "/record/list",
                    type: "post",
                    dataType: "json",
                    data: {
                        pageIndex: this.currentPage,
                        pageSize: this.pageNum
                    },
                    success: function (data) {
                        vueObj.all = data.total
                        vueObj.pageInfo = data.list;
                    }
                });
            },
            detailRecord(id){
                $.ajax({
                    async: false,
                    type: "POST",
                    url:'/record/'+id,
                    dataType: "json",
                    success:function (data) {
                        var keyArray = Object.keys(data)
                        if (keyArray.indexOf("0") > -1){
                            window.location.href = "tab"
                        }else {
                           var dataMap = objToStrMap(data)
                           this.recordDetail = dataMap.get("record");
                           this.preRecord = dataMap.get("preRecord")
                           this.afterRecord = dataMap.get("afterRecord")
                            window.location.href = "tab"
                        }
                        console.info(data);
                    }
                })
            },
            deleteRecord(id) {
                if(confirmDelete()==true){
                    $.ajax({
                        async: false,
                        type: "POST",
                        url:'/record/delete/'+id,
                        dataType: "text",
                        complete:function () {
                            window.location.reload();
                        }
                    })
                }
            },
            editRecord(id){
                $.ajax({
                    async: false,
                    type: "POST",
                    url:'/record/edit/'+id,
                    dataType: "json",
                    complete:function () {
                        window.location.reload();
                    }
                })
            }
        }
    })
}

function confirmDelete() {
    var msg = "您确定要删除吗？";
    if (confirm(msg)==true){
        return true;
    }else{
        return false;
    }
}

function objToStrMap(obj){
    let strMap = new Map();
    for (let k of Object.keys(obj)) {
        strMap.set(k,obj[k]);
    }
    return strMap;
}
/**
 *json转换为map
 */
function jsonToMap(jsonStr){
    return this.objToStrMap(JSON.parse(jsonStr));
}