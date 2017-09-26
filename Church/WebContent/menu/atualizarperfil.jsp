<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Atualizar Perfis</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<jsp:include page="/menu/menu.jsp"></jsp:include>
    <div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Atualizar Perfis</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Atualizar Perfis
                        </div>
                        <div class="panel-body">
                            <div class="row">
                             	<form role="form" name="form" action="ControlePerfil" method="post">
                                	<div class="col-lg-6">
                                		<input type="hidden" name="txtid" value="${per.id}" />
                                		<div class="form-group">
                                            <label>Nome</label>
                                            <input class="form-control" placeholder="Nome" name="name" value="${per.name}" autofocus>
                                            <p class="help-block">Coloque o Nome do Perfil Exemplo: Administração.</p>
                                        </div>
	                                	<div class="form-group">
		                                    <label>Aplicação</label>
		                                    <select name="selecionarAplicacaoAtualizar" onchange="submit()" class="form-control" >
		                                   <option value="${apli.id}" selected>${apli.nome}</option>
			                                   	<c:forEach items="${listarAplicacoes}" var="app">
			                                       	<option value="${app.id}">${app.nome}</option>
			                                   	</c:forEach>
		                                    </select>
		                                </div>
		                                
                                        <div class="form-group">
                                            <table class="table tree table-bordered table-striped table-condensed">
												<thead> 
			                                        <tr>
			                                        	<th></th>
			                                        	<th>Nome Menu</th>
			                                            <th>Link</th>
			                                        </tr>
			                                    </thead>
			                                    <tbody>
			                                    	<c:forEach items="${listarM}" var="men">
														<tr class="treegrid-${men.id}">
															<td><input type="checkbox" name="idmen" value="${men.id}" ${men.check}/></td>
															<td>${men.name}</td><td>${men.link}</td>
														</tr>
														<c:if test="${not empty men.listmenu}">
															<c:forEach items="${men.listmenu}" var="submen">
																<tr class="treegrid-${submen.id} treegrid-parent-${men.id}" >
																	<td><input type="checkbox" name="idsubmen" value="${submen.id}" ${submen.check}/></td>
																	<td>${submen.name}</td><td>${submen.link}</td>
																</tr>
																<c:if test="${not empty men.listmenu}">
																	<c:forEach items="${men.listmenu}" var="submen1">
																		<tr class="treegrid-${submen1.id} treegrid-parent-${submen.id}" >
																			<td><input type="checkbox" name="idsubmen1" value="${submen1.id}" ${submen1.check}/></td>
																			<td>${submen1.name}</td><td>${submen1.link}</td>
																		</tr>
																	</c:forEach>
																</c:if>
															</c:forEach>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
                                        </div>
                                        <div class="form-group text-right" style="margin-top:0px;">
		                                	 <input type="submit" class="btn btn-default" name="btnAtualizar" value="Atualizar" />
		                                 </div>
	                                </div>
	                                <!-- /.col-lg-6 (nested) -->
	                                <div class="col-lg-6">
	                                </div>
                                <!-- /.col-lg-6 (nested) -->
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

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
</body>
</html>