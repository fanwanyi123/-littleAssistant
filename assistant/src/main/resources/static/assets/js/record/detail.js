var vueObj;
$(document).ready(function (e) {
    vueFunction("#recordDetailVue", e);
    vueObj.detailRecord();
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
                            vueObj.recordTitle = data.recordDetail.recordTitle
                            vueObj.recordContent = data.recordDetail.recordContent
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