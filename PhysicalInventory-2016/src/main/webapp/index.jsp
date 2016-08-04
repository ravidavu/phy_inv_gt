<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<!-- build:css(.) styles/vendor.css -->
<!-- bower:css -->
<link rel="stylesheet"
	href="bower_components/bootstrap/dist/css/bootstrap.css" />
<link rel="stylesheet"
	href="bower_components/angular-ui-grid/ui-grid.css" />
<link rel="stylesheet" type="text/css"
	href="styles/easyui/themes/default/easyui.css">

<link rel="stylesheet" href="styles/customCssNew.css" />
<!-- endbower -->
<!-- endbuild -->
<!-- build:css(.tmp) styles/main.css -->
<link rel="stylesheet" href="styles/main.css">
<!-- endbuild -->
</head>
<body ng-app="inventoryApp">
	<!--[if lte IE 8]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

	<!-- Add your site or application content here -->

	<!--  <nav id="myNavbar" class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation"> -->
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="">
		<div class="container-fluid">
			<div class="navbar-blue text-center clearfix"
				style="background-color: #005BB2 !important">
				<span class="pull-left"> <img src="images/logo.jpeg"
					alt="Logo">
				</span> <span class="title">Physical Inventory</span>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="text-center clearfix" id="navbarCollapse">
				<ul class="nav navbar-nav"
					style="margin-top: -1% !important; margin-bottom: -1% !important;">
					<li id="dashboardLi"
						style="padding-top: 13px; padding-bottom: 13px;"><a
						ui-sref="dashboard" style="font-size: 18px">Dashboard</a></li>
					<!-- <li id="obsolescenceLi"><a ui-sref="obsolescence" style="font-size:18px">Obsolescence</a></li>
              <li id="costingLi"><a ui-sref="costing" style="font-size:18px">Costing</a></li> -->
				</ul>
			</div>
		</div>
		<!-- </nav> -->

		<div class="container-fluid">
			<div ui-view="" style="width: 100%; align: right;"></div>


			<div class="footer container">&copy; Copyright &copy; Sherwin
				Williams</div>

		</div>
	</div>
	<!-- <div class="header container">

      <div class="navbar navbar-blue text-center clearfix">
        <span class="pull-left">
          <img src="images/logo.jpeg" alt="Logo">
        </span>
        <span class="title">Physical Inventory</span>
      </div>

      <div style="color:white bold"  role="navigation">
        <div class="">
          <div class="collapse navbar-collapse" id="js-navbar-collapse">

            <ul class="nav navbar-nav">
              <li><a ui-sref="dashboard" style="font-size:18px">Dashboard</a></li>
              <li><a ui-sref="obsolescence" style="font-size:18px">Obsolescence</a></li>
              <li><a ui-sref="costing" style="font-size:18px">Costing</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="page-content container" >
      <div ui-view=""></div>
    </div>

    <div class="footer container">
      <div class="pull-middle">
        <p>Copyright &copy; subrat khilar</p>
      </div>
    </div> -->


	<!-- Google Analytics: change UA-XXXXX-X to be your site's ID -->
	<script>
       !function(A,n,g,u,l,a,r){A.GoogleAnalyticsObject=l,A[l]=A[l]||function(){
       (A[l].q=A[l].q||[]).push(arguments)},A[l].l=+new Date,a=n.createElement(g),
       r=n.getElementsByTagName(g)[0],a.src=u,r.parentNode.insertBefore(a,r)
       }(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

       ga('create', 'UA-XXXXX-X');
       ga('send', 'pageview');
    </script>
	<script>





</script>
	<!-- build:js(.) scripts/vendor.js -->
	<!-- bower:js -->

	<script src="bower_components/jquery/dist/jquery.js"></script>
	<script src="bower_components/angular/angular.js"></script>
	<script src="bower_components/dirPagination/dirPagination.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script
		src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
	<script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
	<script src="bower_components/angular-ui-grid/ui-grid.js"></script>
	<script src="http://alasql.org/console/alasql.min.js"></script>
	<script src="http://alasql.org/console/xlsx.core.min.js"></script>


	<script type="text/javascript" src="bower_components/tableExport.js"></script>
	<script type="text/javascript"
		src="bower_components/jspdf/libs/sprintf.js"></script>
	<script type="text/javascript" src="bower_components/jspdf/jspdf.js"></script>
	<script type="text/javascript"
		src="bower_components/jspdf/libs/base64.js"></script>
	<script type="text/javascript" src="bower_components/FileSaver.js"></script>
	<!-- endbower -->
	<!-- endbuild -->

	<!-- build:js({.tmp,app}) scripts/scripts.js -->
	<script src="scripts/app.js"></script>
	<script src="scripts/controllers/dashboard.js"></script>
	<script src="scripts/controllers/process.js"></script>
	<script src="scripts/controllers/obsolescence.js"></script>
	<script src="scripts/controllers/costing.js"></script>
	<script src="scripts/services/storeService.js"></script>
	<script src="scripts/services/dashbordService.js"></script>
	<script src="scripts/controllers/report.js"></script>

	<!-- endbuild -->
</body>
</html>
