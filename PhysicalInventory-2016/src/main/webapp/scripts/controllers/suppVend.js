var isNewRow = false;
var newIndex = 9999999;

function intializeSuppVendPage() {
	searchboxcategorymenu = $('#searchsuppvendbox').searchbox('menu'); // get
	$.fn.epoSuppVend();
}
$(function() {
	$('#epo_suppvend_create_button_id').click(
			function(event) {
				ajaxCall($("form").serialize(), $("#contextpathid").val()
						+ '/epoSuppVend/create', createSuppVendCallback, null,
						null, null);
			});
	ajaxCall(null, $("#contextpathid").val() + 'tasks',
			loadMenuCallback, null, null, null);

});

$.fn.epoSuppVend = function() {
	setUrl("tasks");
	suppVendUtils.defineGridColumns();
	// setCategoryCols();
	toggleGrid();
	suppVend.generateGrid();

};

var suppVend = {
	deletePgm : function(target) {
		$('#suppvendGrid').datagrid('loading');
		$('#suppvendGrid').datagrid('selectRow', getRowIndex(target));
		$('#suppvendGrid').datagrid('getSelected');
		$('#suppvendGrid').edatagrid('destroyRow', getRowIndex(target));
		$('#suppvendGrid').datagrid('loaded');
		
	},
	cancelPgm : function(target) {
		if (isNewRow && newIndex == getRowIndex(target)) {
			$('#suppvendGrid').datagrid('deleteRow', getRowIndex(target));
			isNewRow = false;
			newIndex = 9999999;
		} else {
			$('#suppvendGrid').datagrid('cancelEdit', getRowIndex(target));
			suppVendUtils.makeColumnsReadOnly();
		}
	},
	save : function(target) {
		if (suppVend.validateGridRow(target)) {
			$('#suppvendGrid').datagrid('loading');
			if (isNewRow && newIndex == getRowIndex(target)) {
				isNewRow = false;
				newIndex = 9999999;
			}
			var opts = $('#suppvendGrid').edatagrid('options');
			opts.editIndex = getRowIndex(target);
			$('#suppvendGrid').datagrid('selectRow', getRowIndex(target));
			$('#suppvendGrid').edatagrid('saveRow');
		}

	},
	search : function(value, name) {
		$('#suppvendGrid').edatagrid('load', {
			searchItem : name,
			searchValue : value
		});
	},
	generateGrid : function() {
		$('#suppvendGrid')
				.edatagrid(
						{
							iconCls : 'icon-edit',
							// width : width,
							destroyMsg : {
								confirm : {
									title : 'Confirm',
									msg : 'Are you sure you want to delete? '
								}
							},
							height : height,
							singleSelect : true,
							idField : 'idfield',
							pagination : true,
							fitColumns : true,
							rownumbers : false,
							striped : true,
							url : url,
							updateUrl : $("#contextpathid").val()
									+ "/epoSuppVend/create",
							destroyUrl : $("#contextpathid").val()
									+ "/epoSuppVend/delete",
							saveUrl : $("#contextpathid").val()
									+ "/epoSuppVend/create",
							columns : cols,
							loader : function(param, success, error) {
								ajaxCall(param, url, loadSuppVendGridCallback,
										null, success, error);
							},
							loadFilter : function(data) {
								return data;
							},
							onBeforeSave : function(index) {
								/*$('#suppvendGrid').datagrid('loading');*/
							},
							onLoadSuccess : function(data) {
								var pager = $('#suppvendGrid').datagrid(
										'getPager'); // get
								// the pager of datagrid
								pager.pagination({
									showPageList : false
								});
							},
							onDestroy : function(index, row) {
								$('#suppvendGrid').datagrid('loaded');
								$('#messages').empty();
								$('#messages')
										.append(
												'<li><span style="color: Green">Delete Successful</span></li>');
							},
							onLoadError : function() {
								alert("error");
							},
							onBeforeEdit : function(index, row) {
								row.editing = true;
								suppVendUtils.updateSuppVendActions(index);
							},
							onAfterEdit : function(index, row) {
								row.editing = false;
								suppVendUtils.updateSuppVendActions(index);
							},
							onCancelEdit : function(index, row) {
								row.editing = false;
								suppVendUtils.updateSuppVendActions(index);
							},
							onClickRow : function(index, row) {
								if (isNewRow && newIndex != index) {
									row.editing = false;
									$('#suppvendGrid').datagrid('cancelEdit',
											index);
									$('#suppvendGrid').datagrid('selectRow',
											newIndex);
									$('#suppvendGrid').datagrid('beginEdit',
											newIndex);
								} else if (isNewRow && newIndex == index) {
									// doNothing
								} else {
									$('#suppvendGrid').datagrid('cancelEdit',
											index);
								}
							},
							onDblClickRow : function(index, row) {
								if (isNewRow && newIndex != index) {
									row.editing = false;
									$('#suppvendGrid').datagrid('cancelEdit',
											index);
									$('#suppvendGrid').datagrid('selectRow',
											newIndex);
									$('#suppvendGrid').datagrid('beginEdit',
											newIndex);
								} else if (isNewRow && newIndex == index) {
									// doNothing
								} else {
									$('#suppvendGrid').datagrid('cancelEdit',
											index);
									$('#suppvendGrid').datagrid(
											'clearSelections');
								}

							},
							/*
							 * onDblClickRow : function(index, row) {
							 * row.editing = false; $('#suppvendGrid')
							 * .datagrid('cancelEdit', index); },
							 */
							onBeforeLoad : function(param) {
								if (isNewRow) {
									$('#suppvendGrid').datagrid('deleteRow',
											newIndex);
									isNewRow = false;
									newIndex = 9999999;
								}
							},
							onSuccess : function(index, row) {
								$('#suppvendGrid').datagrid('loaded');
								//alert('success' + index + JSON.stringify(row));
								isNewRow = false;
								newIndex = 9999999;
								$('#messages').empty();
								$('#messages').append(
										'<li><span style="color: Green">'
												+ row.msg + '</span></li>');
								$('#suppvendGrid').datagrid('clearSelections');
								$('#suppvendGrid').datagrid('reload');
							},
							onError : function(index, row) {
								$('#suppvendGrid').datagrid('loaded');
								// alert('error' + index);
								if (row.msg != '' && row.msg != undefined
										&& row.msg.indexOf('Delete') == -1) {
									isNewRow = true;
									newIndex = index;
									$('#suppvendGrid').datagrid('beginEdit',
											index);
									/*
									 * var rows =
									 * $('#SuppVendGrid').datagrid('getRows',
									 * index); findAndRemove(rows, index);
									 */
								}
								$('#messages').empty();
								$('#messages').append(
										'<li><span style="color: red">'
												+ row.msg + '</span></li>');
							}
						});
	},
	insertGridRow : function() {
		if (!isNewRow) {
			isNewRow = true;
			var row = $('#suppvendGrid').datagrid('getSelected');

			if (row) {
				var index = $('#suppvendGrid').datagrid('getRowIndex', row);

			} else {
				index = 0;

			}
			newIndex = index;
			$('#suppvendGrid').datagrid('insertRow', {
				index : index,
				row : {}
			});
			/*suppVendUtils.makeTextColumnEditor('gnrcCode');
			// enableTextonSuppVendColumn('code');
			suppVendUtils.makeTextColumnEditor('supplier');
			// enableTextonSuppVendColumn('name');
			suppVendUtils.makeTextColumnEditor('vendor');*/
			/* enableComboonSuppVendColumn('type'); */

			$('#suppvendGrid').datagrid('selectRow', index);
			$('#suppvendGrid').datagrid('beginEdit', index);
		} else {
			swal({
				title : "WARNING",
				text : "Unsaved Record Exist. Save or Cancel To Insert New Record",
				type : "warning",
				animation : "slide-from-top"
			});
			// alert('Unsaved Record Exist. Save or Cancel To Insert New
			// Record');
		}
	},
	validateGridRow : function(target) {
		var valueArray = getRowValues(target);
		for ( var i = 0; i < valueArray.length; i++) {
			if (valueArray[i] != undefined && valueArray[i].trim() == '') {
				swal({
					title : "Oops.. Validation Failed",
					text : "Fields Cannot Be Blank",
					type : "error",
					animation : "slide-from-top"
				});
				return false;
			}
		}

		return true;
	}

};

var loadSuppVendGridCallback = {
	success : function(data, status, request, target, success) {
		// alert('success' + JSON.stringify(data));
		obj = JSON.parse(JSON.stringify(data));
		if (request.status == '304') {
			success(data);
			top.location.href = 'jsp/login.jsp';
		} else if (obj != undefined && obj.actionErrors) {
			$("#error").append('<p>' + obj.actionErrors + '</p>');
			success(data);
		} else {

			var jsonText = '{\"total\":' + obj.total + ',\"rows\":' + '['
					+ obj.rows + ']}';
			var jsonObject = eval('(' + jsonText + ')');
			success(jsonObject);
		}
	},
	error : function(request, status, error, target, errorFunc) {
		// errorFunc(request);
		alert('error loader' + error);
	},
	beforeSend : function(request, settings) {
		$('ul.actionMessage').empty();
		return true;
	}

};

var createSuppVendCallback = {
	success : function(data, status, request, target, successFunc) {
		obj = JSON.parse(JSON.stringify(data));
		if (obj != undefined && obj.msg != undefined
				&& obj.msg.indexOf('SUCCESSFUL') != -1) {
			top.location.href = $("#contextpathid").val() + '/tasks';
		} else {

			$('ul.actionMessage').append(
					'<li><span style="color: red">' + obj.msg + '</span></li>');
		}
	},
	error : function(request, status, error, target, errorFunc) {
		alert('inside error' + request + status + error);
	},
	beforeSend : function(request, settings) {
		var isValid = true;
		$("form").validate();
		if (!$("input[@name='type']:checked").val()) {
			$('#type_id-error').remove();
			$('#programTypesIdDiv')
					.append(
							'<label id="type_id-error" class="control-label col-md-4" style="color:red">This field is required.</label>');
			isValid = false;
		} else {
			$('type_id-error').remove();
		}
		if (!$("form").valid()) {
			isValid = false;
		}
		return isValid;
	}
};

var suppVendUtils = {
	updateSuppVendActions : function(index) {
		$('#suppvendGrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	},
	makeTextColumnEditor : function(column_name) {
		var opts = $('#suppvendGrid').datagrid('getColumnOption', column_name);
		// alert(opts);
		opts.editor = {
			type : 'text'
		};
		/*
		 * opts.styler = function(value, rowData, rowIndex) {
		 * //alert(value+JSON.stringify(rowData)+rowIndex); return
		 * "background-color:green"; };
		 */
		// alert(JSON.stringify(opts));
	},
	makeNullColumnEditor : function(column_name) {
		var opts = $('#suppvendGrid').datagrid('getColumnOption', column_name);
		opts.editor = {
			type : null
		};

	},
	makeColumnsReadOnly : function() {
		/*suppVendUtils.makeNullColumnEditor('gnrcCode');
		suppVendUtils.makeNullColumnEditor('supplier');
		suppVendUtils.makeNullColumnEditor('vendor');*/
		/*
		 * enableNullonSuppVendColumn('code');
		 * enableNullonSuppVendColumn('name');
		 * enableNullonSuppVendColumn('type');
		 */
	},
	defineGridColumns : function() {
		cols = [ [
				{
					field : 'pId',
					title : 'S No',
					width : 80,
					editor : null,
					styler : function(value, rowData, rowIndex) {
						return {
							style : "maxlength='20'"
						};
					}
				},
				{
					field : 'processName',
					title : 'PROCESS',
					width : 100,
					editor : null
				},
				{
					field : 'status',
					title : 'STATUS',
					width : 80,
					editor : null
				}
				
				] ];
	}
};
