var vueObj;
var recordId = $("#recordId").val();
$(document).ready(function (e) {
    vueFunction("#recordDetailVue", e);
    vueObj.detailRecord();
    increaseViewCount(recordId)
});

function vueFunction(el, e) {
    vueObj = new Vue({
            el: el,
            data: {
                recordDetail: [],
                recordTitle: "",
                recordContent: "",
                preRecord: "",
                afterRecord: "",
                recordId: $("#recordId").val(),
                recordFiles: [],
            },
            e: e,
            methods: {
                goBack() {
                    window.location.href = getRootPath() + "record";
                },
                detailRecord() {
                    $.ajax({
                        url: getRootPath() + "/record/" + this.recordId,
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            vueObj.recordDetail = data.recordDetail;
                            vueObj.preRecord = data.preRecord;
                            vueObj.afterRecord = data.afterRecord;
                            vueObj.recordTitle = data.recordDetail.recordTitle;
                            vueObj.recordContent = data.recordDetail.recordContent;
                            vueObj.recordFiles = data.recordFiles;
                            $("#recordContent").html(vueObj.recordContent)
                        }
                    })
                },
                pageJump(id) {
                    window.location.href = getRootPath() + '/record/detail/' + id;
                },
                editRecord(id) {
                    window.location.href = getRootPath() + '/record/edit/' + id;
                },
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


//文章浏览量+1
function increaseViewCount(recordId) {
    if ($.cookie("viewId") != recordId || $.cookie("viewId") == null) {
        $.ajax({
            async: false,
            type: "POST",
            url: getRootPath() + "/record/view/" + recordId,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                $(".recordViewCount").html(data);
                $.cookie(
                    "viewId",
                    recordId,//需要cookie写入的业务
                    {
                        "path": "/", //cookie的默认属性
                    }
                );
            },
            error: function () {
                alert("获取数据出错!");
            },
        });
    }
}