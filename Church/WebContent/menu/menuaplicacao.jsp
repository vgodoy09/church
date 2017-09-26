<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@page contentType="text/html" pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html>
<html>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="bower_components/metisMenu/dist/metisMenu.min.css" />
        <!-- Timeline CSS -->
    	<link href="dist/css/timeline.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="dist/css/sb-admin-2.css" />
        <link href="bower_components/morrisjs/morris.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.min.css" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
        <title>Menu</title>
    </head>

    <body>
	    <div id="wrapper">
	
	        <!-- Navigation -->
<!-- 	        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0"> -->
		        <div class="navbar-default sidebar" role="navigation">
	                <div class="sidebar-nav navbar-collapse">
	                    <ul class="nav" id="side-menu">
	                        <li class="sidebar-search">
	                            <div class="input-group custom-search-form">
	                                <input type="text" class="form-control" placeholder="Search...">
	                                <span class="input-group-btn">
	                                <button class="btn btn-default" type="button">
	                                    <i class="fa fa-search"></i>
	                                </button>
	                            </span>
	                            </div>
	                            <!-- /input-group -->
	                        </li>
	                        <c:forEach items="${listarMenus}" var="mm">
	                        	<c:if test="">
	                        		<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> ${mm.id}<span class="fa arrow"></span></a>
	                        		<c:forEach items="${listarSubMenus}" var="sm">
				                        <li>
				                            <ul class="nav nav-second-level">
				                                <li>
				                                    <a href="${sm.link}">${sm.name}</a>
				                                </li>
				                            </ul>
				                            <!-- /.nav-second-level -->
			                        	</li>
			                        </c:forEach>
		                        </c:if>
		                        <li>
		                            <a href="${mm.link}"><i class="fa fa-table fa-fw"></i> ${mm.name}</a>
		                        </li>
	                        </c:forEach>
	                    </ul>
	                </div>
	                <!-- /.sidebar-collapse -->
	            </div>
<!-- 	       </nav> -->
		</div>
	    <!-- /#wrapper -->
	
	    <!-- jQuery -->
	    <script src="bower_components/jquery/dist/jquery.min.js"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
	
	    <!-- Morris Charts JavaScript -->
	    <script src="bower_components/raphael/raphael-min.js"></script>
	    <script src="bower_components/morrisjs/morris.min.js"></script>
	    <script src="js/morris-data.js"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="dist/js/sb-admin-2.js"></script>
    	
    </body>
</html>
