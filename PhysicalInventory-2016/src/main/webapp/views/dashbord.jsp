
<div class=" " style="margin-bottom: 0px; height: 100%;">

	<div class="" ng-controller="DashboardCtrl as ctrl"
		style="width: 100%;">
		<table class="table table-striped table-hover table-bordered"
			data-toggle="table"
			style="width: 55%;margin-left:8%; border: 1px; border-color: black; background-color: white!mportant;">
			<tbody>
				<tr style="width: 100%">
					<td
						style="background-color: white; margin-bottom: 0px !important; padding-bottom: 0px !important; margin-left: 0px !important; padding-left: 0px !important; margin-top: 0px !important; padding-top: 0px !important;padding-right: 0px !important;">
						<div
							style="background-color: #e0ecff; width: 100%; height:25px;">
							<a id="collapse3anchor" data-toggle="collapse"
								data-target="#collapse3" href="#collapse3"
								style="font-size: 20px; margin-left: 15px !important;">
								Dashboard </a>
						</div>
						<table id="table1"
							class="table table-striped table-hover table-bordered"
							style="margin-right: 0px !important; padding-right: 0px !important; margin-bottom: 0px !important; padding-bottom: 0px !important; width: 100% !important"
							data-toggle="table">
							<thead>
								<tr class="theader">
									<th>Process</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Stores Taking Table</td>
									<td><a ui-sref="process">Create New</a></td>
								</tr>
								<tr>
									<td>Forced obsolescence</td>
                <td><a ui-sref="obsolescence">Obsolescence</a>
                
									</td>
								</tr>
								<tr>
									<td>Costing</td>
									<td><a ui-sref="costing">Costing</a>
									</td>
								</tr>
								<tr>
									<td>Report</td>
									<td><b>InProcess</b></td>
								</tr>

							</tbody>
						</table>
					</td>

				</tr>
			</tbody>


		</table>
	</div>
</div>
<script>
	$(document).ready(function() {
		$("#dashboardLi").click(function() {
			$("#dashboardLi").addClass("active");
			$("#obsolescenceLi").removeClass("active");
			$("#costingLi").removeClass("active");
		});
	});
</script>
<style>
.selected {
	background-color: #ffff80 !important;
}
</style>
<!-- <div class="clearfix">
	<div class="pull-right">
		<a class="btn btn-sm btn-primary" ui-sref="process">Create Process</a>
	</div>
</div> -->

<!-- <script src="bower_components/jquery/dist/jquery.js"></script>
<script src="bower_components/angular/angular.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script
	src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="bower_components/angular-ui-grid/ui-grid.js"></script> -->
