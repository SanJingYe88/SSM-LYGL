$(document).ready(function() {
	// 在 queryFormId 表单的 btn-search 元素上注册 click 事件
	$("#queryFormId").on("click", ".btn-search", doQueryObjects);
	// 在禁用和启用按钮上注册点击事件
	$("#queryFormId").on("click", ".btn-valid,.btn-invalid", doValidById);
	// 在添加,修改按钮上注册点击事件,模态框
	$('#queryFormId').on("click", ".btn-add,.btn-update", doShowEditDialog);
	// 初始化页面数据
	doGetObjects();
});

// 弹出模态框, 在模态框内部显示project_edit.jsp
function doShowEditDialog() {
	var title;
	// 1.定义模态框的标题
	if ($(this).hasClass("btn-add")) {
		title = "添加项目"
	}
	if ($(this).hasClass("btn-update")) {
		// 模态框上绑定id值(在修改页面中要根据此值获取数据)
		// data(key,value)函数用于绑定数据
		// data(key)函数用于获取数据
		$("#modal-dialog").data("id", $(this).parent().parent().data("id"));
		// 定义修改时的标题
		title = "修改项目,id为" + $("#modal-dialog").data("id");
	}
	// 2.启动模态框,并加载页面
	// 在模态框对应位置异步加载url
	var url = "project/editUI.do";
	$("#modal-dialog .modal-body").load(url, function() {// 异步加载完成回调此函数
		// 设置标题内容
		$(".modal-title").html(title);
		// 显示模态框(index.jsp中)
		$("#modal-dialog").modal("show");
	});
}

//执行禁用启用操作 
// 1.获得数据(禁用或启动哪些项目信息) a)id(选中的那个checkbox的值) b)valid(由点击按钮决定)
// 2.发送异步请求,修改记录信息.     a)url b)params c)post(url,params,functions(result){})
function doValidById() {
	// 1.获得页面数据(valid,checkedIds)
	// 1.1 获得valid的值(根据点击的按钮)
	var valid; // 定义一个变量(默认值undefined)
	// 判定点击的按钮是启用还是禁用
	if ($(this).hasClass("btn-valid")) {
		valid = 1; // 表示启用
	}
	if ($(this).hasClass("btn-invalid")) {
		valid = 0; // 表示禁用
	}
	// 1.2获得选中的id值(可能1个也可能是多个)
	var checkedIds = getCheckedIds();
	if (checkedIds.length == 0) {
		alert("请至少选择一项");
		return;
	}
	// 2.提交异步请求,更新对应记录的状态信息
	var url = "project/doValidById.do"
	var params = {
		"checkedIds" : checkedIds,
		"valid" : valid
	};
	$.post(url, params, function(result) {
		if (result.state == 1) {
			alert(result.message);
			// 重新查询
			doGetObjects();
		} else {
			alert(result.message);
		}
	});
}

// 获取选中的选项
function getCheckedIds() {		// 1,2,3,4,5
	var checkedIds = "";
	// 1.遍历所有的checkbox,获得选中的值
	$('#tbodyId input[name="checkedItem"]').each(function() {
		// 判定当前对象是否是选中的
		if ($(this).prop("checked")) {
			if (checkedIds == "") {
				checkedIds += $(this).val();
			} else {
				checkedIds += "," + $(this).val();
			}
		}
	});
	return checkedIds;
}

// 查询
function doQueryObjects() {
	// 1.修改当前页的值为1
	$("#pageId").data("pageCurrent", 1);
	// 2.执行查询动作(重用doGetObjects方法)
	doGetObjects();
}

// 获得查询表单中的数据
function getQueryFormData() {
	// 根据id获得具体对象的值,然后封装到JSON对象
	var params = {
		name : $("#searchNameId").val(),
		valid : $("#searchValidId").val()
	};
	return params;
}

function doGetObjects() {
	///ttms/product/controller/ProjectController.java
	var url = "project/doFindObjects.do"

	// 获取当前页的页码值,假如没有值,默认值设置为1
	var pageCurrent = $("#pageId").data("pageCurrent");
	if (!pageCurrent) {
		pageCurrent = 1;
	}

	// 定义一个params对象
	var params = getQueryFormData();
	// 动态的向params对象中添加key/value
	params.pageCurrent = pageCurrent;
	$.post(url, params, function(result) {
		// 将json对象中的数据,填充到table的tbody元素中
		if (result.state == 1) { // 成功
			// 显示记录信息
			setTableBodyRows(result.data.list); // data属性的值对应一个json对象
			// 设置及显示分页信息
			setPagination(result.data.pageObject);
		} else {
			alert(result.message);
		}
	});

}

// 定义函数将json对象中的数据取出来填充到表格中
function setTableBodyRows(result) {
	// 获得tbody对象(根据id获得)
	var tBody = $("#tbodyId");
	// 清空body中内容
	tBody.empty();
	// 迭代json对象
	for ( var i in result) { // 循环一次取一行[{"name":"AAA",...},{},{}]
		var tr = $("<tr></tr>");
		tr.data("id", result[i].id);
		// tr对象中追加td字符串对象
		var firstTd = '<td><input type="checkbox" name="checkedItem" value="[id]"></td>';
		// 将firstTd字符串中的[id]替换为一个具体指
		firstTd = firstTd.replace("[id]", result[i].id);
		tr.append(firstTd);
		tr.append("<td>" + result[i].code + "</td>");
		tr.append("<td>" + result[i].name + "</td>");
		tr.append("<td>" + result[i].beginDate + "</td>");
		tr.append("<td>" + result[i].endDate + "</td>");
		tr.append("<td>" + (result[i].valid ? "启用" : "禁用") + "</td>");
		tr.append("<td><button type='button' class='btn btn-default btn-update'>修改</button></td>");
		//将每一行记录再追加到tbody中
		tBody.append(tr);
	}
}