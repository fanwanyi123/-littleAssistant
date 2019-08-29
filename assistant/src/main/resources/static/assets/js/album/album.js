var vueObj;
$(document).ready(function (e) {
    vueFunction("#vuePicture", e);
});

function vueFunction(el, e) {
    vueObj = new Vue({
            el: el,
            data: {
                fileList: [],
                fileData: ""
            },
            e: e,
            methods: {
                handleRemove(file, fileList) {
                    console.log(file, fileList);
                },
                handlePreview(file) {
                    console.log(file);
                },
                uploadFile(){
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        url: getRootPath() + "/upload/file",
                        data: vueObj.fileData,
                        contentType: false,//这里不要落下
                        processData: false,
                        async: false,
                        success: function (data) {
                            window.location.href = getRootPath() + "/record";
                        },
                        error: function (error) {
                            window.location.href = getRootPath() + "/record";
                        }
                    });
                },
            }
        }
    )
}