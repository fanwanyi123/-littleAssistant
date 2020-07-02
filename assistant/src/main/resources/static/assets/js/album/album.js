let vueObj;
$(document).ready(function (e) {
    vueFunction("#vuePicture", e);
});

function vueFunction(el, e) {
    vueObj = new Vue({
        el: el,
        data: {
            file: [],
            fileData: ""
        },
        e: e,
        methods: {
            //开始上传文件
            // 自定义的上传函数
            httpRequest(param) {
                // 一般情况下是在这里创建FormData对象，但我们需要上传多个文件，为避免发送多次请求，因此在这里只进行文件的获取，param可以拿到文件上传的所有信息
                this.file.push(param.file)
            },
            submitUpload() {
                let formData = new FormData() // 首先创建FormData对象
                this.$refs.upload.submit() // 这里是执行文件上传的函数，其实也就是获取我们要上传的文件
                this.file.forEach(function (file) {
                    formData.append('file', file, file.name); // 因为要上传多个文件，所以需要遍历一下才行
                    // upData.append('file', this.file); 不要直接使用我们的文件数组进行上传，你会发现传给后台的是两个Object
                })
                this.$http.post('/upload/file', JSON.stringify(formData),e).then(function (res) {
                    res.json().then(function (result) {
                        console.log(result);
                    });
                }, function (res) {
                    console.log(res.body);
                });
            }
        }
    })
}

