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
            showThird: false
        },
        e: e,
        methods:{
            getChildTag: function () {
                var articleParentCategoryId = $("#articleParentCategoryId").val();
                if (articleParentCategoryId == 1){
                    $("#tagIcon").attr("style","color:green;");
                } else if (articleParentCategoryId == 2){
                    $("#tagIcon").attr("style","color:yellow");
                } else {
                    $("#tagIcon").attr("style","color:red");
                }
                vm.showSecond = false;
                vm.childTagList = [];
                if (articleParentCategoryId){
                    for (var i=0; i < vm.categoryList.length;i++){
                        if (articleParentCategoryId == vm.categoryList[i].pid){
                            var set = new Set(vm.childTagList);
                            set.add(vm.categoryList[i]);
                            vm.childTagList = Array.from(set);
                            vm.showSecond = true
                        }
                    }
                }
            },
            getGrandSonTag: function () {
                var articleChildCategoryId = $("#articleChildCategoryId").val();
                vm.showThird = false;
                vm.grandsonList = [];
                if (articleChildCategoryId){
                    for (var i=0; i < vm.categoryList.length;i++){
                        if (articleChildCategoryId == vm.categoryList[i].pid){
                            var set = new Set(vm.grandsonList);
                            set.add(vm.categoryList[i]);
                            vm.grandsonList = Array.from(set)
                            vm.showThird = true;
                        }
                    }
                }
            }
        }
    })
}


function initCategory(){
    $.ajax({
        url: "/record/getCategory",
        type: "post",
        dataType: "json",
        success: function (data) {
            vm.categoryList = data;
        }
    });
}