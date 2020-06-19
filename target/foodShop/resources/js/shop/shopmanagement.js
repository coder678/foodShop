$(function() {
	var shopId = getQueryString('shopId');   //获取店铺的Id
	var shopInfoUrl = '/foodShop/shopadmin/getshopmanagementinfo?shopId=' + shopId;
	$.getJSON(shopInfoUrl, function(data) {
		if (data.redirect) {
			window.location.href = data.url;
		} else {
			if (data.shopId != undefined && data.shopId != null) {
				shopId = data.shopId;
			}
			$('#shopInfo')
					.attr('href', '/foodShop/shopadmin/shopoperation?shopId=' + shopId);   //转发到/foodShop/shopadmin/shopoperation
		}                                                                                 //对这个shopId的店铺进行修改
	});
});