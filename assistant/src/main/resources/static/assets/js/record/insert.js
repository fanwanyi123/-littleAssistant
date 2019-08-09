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
            valueOne: [],
            valueTwo: [],
            valueThree: []
        },
        e: e,
        methods:{
            getChildTag: function (val) {
                if (val == 1){
                    $("#tagIcon").attr("style","color:green;");
                } else if (val == 2){
                    $("#tagIcon").attr("style","color:yellow");
                } else {
                    $("#tagIcon").attr("style","color:red");
                }
                vm.showSecond = false;
                vm.childTagList = [];
                if (val){
                    for (var i=0; i < vm.categoryList.length;i++){
                        if (val == vm.categoryList[i].pid){
                            var set = new Set(vm.childTagList);
                            set.add(vm.categoryList[i]);
                            vm.childTagList = Array.from(set);
                            vm.showSecond = true
                        }
                    }
                }
            },
            getGrandSonTag: function (val) {
                vm.showThird = false;
                vm.grandsonList = [];
                if (val){
                    for (var i=0; i < vm.categoryList.length;i++){
                        if (val == vm.categoryList[i].pid){
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