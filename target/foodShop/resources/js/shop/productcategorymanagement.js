$(function () {
    var listUrl = '/foodShop/shopadmin/getproductcategorylist';
    var addUrl = '/foodShop/shopadmin/addproductcategorys';
    var deleteUrl = '/foodShop/shopadmin/removeproductcategory';
    getList();             //在刚刚进入页面，就加载商品分类的数据

    function getList() {
        $.getJSON(
            listUrl,
            function (data) {
                if (data.success) {
                    var dataList = data.data;                      //获取url中的数据
                    $('.category-wrap').html('');
                    var tempHtml = '';
                    dataList.map(function (item, index) {
                            tempHtml += ''
                                + '<div class="row row-product-category now">'    //将所有的分类都放到列表中
                                + '<div class="col-33 product-category-name">'
                                + item.productCategoryName
                                + '</div>'
                                + '<div class="col-33">'
                                + item.priority
                                + '</div>'
                                + '<div class="col-33"><a href="#" class="button delete" data-id="'
                                + item.productCategoryId
                                + '">删除</a></div>'
                                + '</div>';
                        });
                    $('.category-wrap').append(tempHtml);               //将数据展示到页面中
                }
            });
    }

    $('#new')
        .click(
            function () {    //新增按钮被点击之后，会在html拼接一段用来新增的输入框  新增的部分通过 div中class属性含有 temp来分辨
                var tempHtml = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                    + '</div>';
                $('.category-wrap').append(tempHtml);  //追加渲染到页面上
            });


    $('#submit').click(function () {
        var tempArr = $('.temp');    //在提交按钮被点击之后，通过 .temp 中获得要添加的数据
        var productCategoryList = [];
        tempArr.map(function (index, item) {    //遍历每一个新增的商品分类
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();  //遍历获得每一个商品分类的值
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);             //将数据放到  字符串中
            }
        });
        $.ajax({            //通过JSON发送到后端
            url: addUrl,     //发送到批量添加的url，后端进行批量添加处理
            type: 'POST',
            data: JSON.stringify(productCategoryList),    //将数据通过JSON字符串的方式发送
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                    getList();               //在添加成功之后，又重新遍历商品分类，并且渲染到商品种类中
                } else {
                    $.toast('提交失败！');
                }
            }
        });
    });



    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();      //删除掉点击新增按钮产生的数据和文本框

        });
    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;
            $.confirm('确定么?', function () {
                $.ajax({
                    url: deleteUrl,             //通过url路由到后台删除掉数据库的中数据
                    type: 'POST',
                    data: {
                        productCategoryId: target.dataset.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.toast('删除成功！');
                            getList();         //在删除成功之后，就重新加载一个商品列表
                        } else {
                            $.toast('删除失败！');
                        }
                    }
                });
            });
        });
});