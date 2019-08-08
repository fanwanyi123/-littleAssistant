var vueObj;
$(document).ready(function (e) {
    vueFunction("#vueRecord", e);
    vueObj.getPageData();
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
                recordDelete: false,
            },
            e: e,
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
                        url: getRootPath() + "/record/list",
                        type: "post",
                        dataType: "json",
                        data: {
                            pageIndex: this.currentPage,
                            pageSize: this.pageNum
                        },
                        success: function (data) {
                            vueObj.all = data.total
                            vueObj.pageInfo = data.list;
                            console.log(vueObj.all)
                        }
                    });
                },
                deleteRecord(id) {
                    this.$confirm('您确定要删除吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: getRootPath() + '/record/delete/' + id,
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            dataType: "text",
                            complete: function () {
                                this.$message({
                                    type: 'success',
                                    message: '删除成功!'
                                });
                                window.history.pushState(200, document.title, getRootPath() + '/record');
                            }
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
                },
                detailRecord(id, status) {
                    if (status == 1) {
                        window.location.href = getRootPath() + '/record/detail/' + id;
                    } else {
                        window.location.href = getRootPath() + '/record/edit/' + id;
                    }
                },
                editRecord(id) {
                    window.location.href = getRootPath() + '/record/edit/' + id;
                },
                resetDateFilter() {
                    this.$refs.filterTable.clearFilter('date');
                },
                clearFilter() {
                    this.$refs.filterTable.clearFilter();
                },
                formatter(row, column) {
                    return row.address;
                },
                filterTag(value, row) {
                    return row.tag === value;
                },
                filterHandler(value, row, column) {
                    const property = column['property'];
                    return row[property] === value;
                }
            }
        }
    )
}

function objToStrMap(obj) {
    let strMap = new Map();
    for (let k of Object.keys(obj)) {
        strMap.set(k, obj[k]);
    }
    return strMap;
}

/**
 *json转换为map
 */
function jsonToMap(jsonStr) {
    return this.objToStrMap(JSON.parse(jsonStr));
}