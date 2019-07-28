var vueObj;
$(document).ready(function (e) {
    vueFunction("#vueTable", e);
    // initDataList();
});

function vueFunction(el, e) {
    vueObj = new Vue({
        el: el,
        data: {
            all: 1,
            pageInfo: [],
            pageNum: 5,
            currentPage: 1,
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
        }
    })
}


// function initDataList() {
//     $.ajax({
//         url: "/record/list",
//         type: "post",
//         dataType: "json",
//         data: {
//             pageIndex: vueObj.cur
//         },
//         success: function (data) {
//             vueObj.all = data.pages
//             vueObj.pageInfo = data.list;
//         }
//     });
// }