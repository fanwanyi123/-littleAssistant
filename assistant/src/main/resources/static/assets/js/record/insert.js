var vm;
$(document).ready(function (e) {
    vueFunction("#app",e);
    initCategory();
});

function vueFunction(el,e) {
     vm = new Vue({
        el: el,
        data: {
            categoryList: [],
            childTagList: [],
            grandsonList: [],
            showSecond: false,
            showThird: false,
            value: []
        },
        e: e,
        methods:{
            getChildTag: function (val) {
                var recordParentCategoryId = $("#recordParentCategoryId").val();
                if (recordParentCategoryId == 1){
                    $("#tagIcon").attr("style","color:green;");
                } else if (recordParentCategoryId == 2){
                    $("#tagIcon").attr("style","color:yellow");
                } else {
                    $("#tagIcon").attr("style","color:red");
                }
                vm.showSecond = false;
                vm.childTagList = [];
                if (recordParentCategoryId){
                    for (var i=0; i < vm.categoryList.length;i++){
                        if (recordParentCategoryId == vm.categoryList[i].pid){
                            var set = new Set(vm.childTagList);
                            set.add(vm.categoryList[i]);
                            vm.childTagList = Array.from(set);
                            vm.showSecond = true
                        }
                    }
                }
            },
            getGrandSonTag: function () {
                var recordChildCategoryId = $("#recordChildCategoryId").val();
                vm.showThird = false;
                vm.grandsonList = [];
                if (recordChildCategoryId){
                    for (var i=0; i < vm.categoryList.length;i++){
                        if (recordChildCategoryId == vm.categoryList[i].pid){
                            var set = new Set(vm.grandsonList);
                            set.add(vm.categoryList[i]);
                            vm.grandsonList = Array.from(set)
                            vm.showThird = true;
                        }
                    }
                }
            },
        }
    })
}


function initCategory(){
    $.ajax({
        url: getRootPath() + "/record/getCategory",
        type: "post",
        dataType: "json",
        success: function (data) {
            vm.categoryList = data;
        }
    });
}