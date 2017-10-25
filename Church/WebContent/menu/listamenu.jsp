<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link href="css/jquery.treegrid.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Listar Menus</title>
</head>
<body>
	<jsp:include page="/menu/menu.jsp"></jsp:include>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Listar Menus</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Listar Menus
                        </div>
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                             	<form role="form" name="form" action="ControleMenu" method="post">
                             		<div class="form-group">
	                                    <label>Aplicação</label>
	                                    <select name="selecionarAplicacao" onchange="submit()" class="form-control" >
	                                   	<option selected></option>
		                                   	<c:forEach items="${listarAplicacoes}" var="app">
		                                       	<option value="${app.id}">${app.nome}</option>
		                                   	</c:forEach>
	                                    </select>
	                                </div>
                             		<table class="table tree table-bordered table-striped table-condensed">
										<thead> 
	                                        <tr>
	                                        	<th>Nome Menu</th>
	                                            <th>Link</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    	<c:forEach items="${listarM}" var="men">
												<tr class="treegrid-${men.id}" onclick="location.href = 'ControleMenu?antesAtualizar=&id=${men.id}';">
													<td>${men.name}</td><td>${men.link}</td>
												</tr>
												<c:if test="${not empty men.listmenu}">
													<c:forEach items="${men.listmenu}" var="submen">
														<tr class="treegrid-${submen.id} treegrid-parent-${men.id}" onclick="location.href = 'ControleMenu?antesAtualizar=&id=${submen.id}';">
															<td>${submen.name}</td><td>${submen.link}</td>
														</tr>
														<c:if test="${not empty men.listmenu}">
															<c:forEach items="${men.listmenu}" var="submen1">
																<tr class="treegrid-${submen1.id} treegrid-parent-${submen.id}" onclick="location.href = 'ControleMenu?antesAtualizar=&id=${submen1.id}';">
																	<td>${submen1.name}</td><td>${submen1.link}</td>
																</tr>
															</c:forEach>
														</c:if>
													</c:forEach>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
	                                <input type="submit" class="btn btn-default" name="btnNovo" value="Novo" />
                                </form>
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
	
	<script  src="js/jquery.treegrid.js"></script>
	<script  src="js/jquery.treegrid.bootstrap3.js"></script>
	<script>
		function submit(){
			document.forms[0].action = "/ControleMenu?selecionarAplicacao=&idapp=" + combo.options[ combo.selectedIndex ].value;
		}
	</script>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $('.tree').treegrid();
	    });
	</script>
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>
    
</body>
</html>