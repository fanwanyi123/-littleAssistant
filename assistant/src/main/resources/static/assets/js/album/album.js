var vueObj;
$(document).ready(function (e) {
    vueFunction("#vuePicture", e);
});

function vueFunction(el, e) {
    vueObj = new Vue({
            el: el,
            data: {
                fileList: []
            },
            e: e,
            methods: {
                handleRemove(file, fileList) {
                    console.log(file, fileList);
                },
                handlePreview(file) {
                    console.log(file);
                }
            }
        }
    )
}