// 查询用户菜单
function doGetUserMenus() {
	/// common/controller/IndexController
	var url = 'doFindUserMenus.do';
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			//设置菜单
			doSetMenus(result.data);
		} else {
			alert(result.message);
		}
	});
}

//设置菜单
function doSetMenus(list) {
	// 加载一级菜单
	var firstLevelMenus = $('#menu-nav');
	for ( var i in list) {
		if (list[i].parentId == null || list[i].parentId == '') {	//一级菜单的 parentId 为null
			var template = '<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"'
							+ ' role="button" aria-haspopup="true" aria-expanded="false">'
							+ list[i].name
							+ '<span class='
							+ '"caret"></span></a><ul class="dropdown-menu"  id="menu'
							+ list[i].id + '"></ul></li>';
			firstLevelMenus.append(template);
		}
	}
	// 加载二级菜单
	for ( var i in list) {
		if (list[i].parentId != null && list[i].parentId != '') {	//二级菜单的 parentId 不为null
			var secondLevelMenu = $('#menu' + list[i].parentId);
			var li = $('<li></li>');
			//
			li.data('url', list[i].url);
			var temp = '<a  class="menuBtn">' + list[i].name + '</a>';
			temp = li.append(temp);
			var separetor = '<li role="separator" class="divider"></li>';
			secondLevelMenu.append(temp).append(separetor);
		}
	}
}

function doLoadUrl() {
	var url = $(this).parent().data('url');
	url = url + '?t=;' + Math.random(1000);
	// 给 index.jsp 页面的 container 动态的加载内容
	$("#container").load(url);
}
