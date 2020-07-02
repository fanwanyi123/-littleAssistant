var vm;
$(document).ready(function (e) {
    vueFunction("#app", e);
    initCategory();
    // Forum Note Active
    $('#summernote').summernote({
        height: 410,
        minHeight: null,
        maxHeight: null,
        focus: true
    });
});

function vueFunction(el, e) {
    vm = new Vue({
        el: el,
        data: {
            categoryList: [],
            childTagList: [],
            grandsonList: [],
            showSecond: false,
            showThird: false,
            fileList: [],
            formDatas: "",
            ruleForm: {
                title: '',
                content: '',
                valueOne: "",
                valueTwo: "",
                valueThree: "",
            },
            rules: {
                title: [
                    {required: true, message: '标题不能为空', trigger: 'blur'},
                ],
                content: [
                    {required: true, message: '请输入内容', trigger: 'blur'}
                ],
            }
        },
        e: e,
        methods: {
            getChildTag: function (val) {
                if (val == 1) {
                    $("#tagIcon").attr("style", "color:green;");
                } else if (val == 2) {
                    $("#tagIcon").attr("style", "color:yellow");
                } else {
                    $("#tagIcon").attr("style", "color:red");
                }
                vm.showSecond = true
                vm.childTagList = [];
                if (val) {
                    for (var i = 0; i < vm.categoryList.length; i++) {
                        if (val == vm.categoryList[i].pid) {
                            var set = new Set(vm.childTagList);
                            set.add(vm.categoryList[i]);
                            vm.childTagList = Array.from(set);
                        }
                    }
                }
            },
            getGrandSonTag: function (val) {
                vm.showThird = false;
                vm.grandsonList = [];
                if (val) {
                    for (var i = 0; i < vm.categoryList.length; i++) {
                        if (val == vm.categoryList[i].pid) {
                            var set = new Set(vm.grandsonList);
                            set.add(vm.categoryList[i]);
                            vm.grandsonList = Array.from(set)
                            vm.showThird = true;
                        }
                    }
                }
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
        }
    })
}


function initCategory() {
    var data = [""]
    $.ajax({
        url: getRootPath() + "/record/getCategory",
        type: "post",
        dataType: "json",
        success: function (data) {
            vm.categoryList = data;
        }
    });
}


function checkSub() {
    if ($("#recordTitle").text() == "" || $("#summernote").text() == "") {

        vm.$notify.error({
            title: '错误',
            message: '输入内容不能为空',
            position: 'bottom-left',
            offset: 150
        });
        return false;
    } else {
        return true;//不写此返回值也行，此时就直接提交了
    }
}