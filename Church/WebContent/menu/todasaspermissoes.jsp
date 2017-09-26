<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="bower_components/metisMenu/dist/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="dist/css/sb-admin-2.css" />
<link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.min.css" />
<title>Permissão</title>
</head>
<body>
	<jsp:include page="/menu/menu.jsp"></jsp:include>
	<div id="page-wrapper">
	   <div class="row">
           <div class="col-lg-12">
               <h1 class="page-header">Permissão</h1>
           </div>
           <!-- /.col-lg-12 -->
       </div>
       <div class="row">
	       <div class="col-lg-12">
		       <div class="panel panel-default">
		       	   <div class="panel-heading">
		                Permissão
		           </div>
				   <div class="panel-body">
				   		<ul class="nav nav-tabs">
						  <li class="active"><a data-toggle="tab" href="#home">Perfis</a></li>
						  <li><a data-toggle="tab" href="#menu1">Igreja</a></li>
						  <li><a data-toggle="tab" href="#menu2">Departamento</a></li>
						</ul>
						
						<div class="tab-content">
						  <div id="home" class="tab-pane fade in active">
						    <div class="row">
						    	<form role="form" name="form" action="ControlePermissao" method="post">
						    		<div class="form-group">
								    	<div class="col-lg-6">
								    		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			                                    <thead>
			                                        <tr>
			                                            <th>Nome da Aplicação</th>	                                            
			                                        </tr>
			                                    </thead>
			                                    <tbody>
													<c:forEach items="${listarAplicacoes}" var="app">
				                                        <tr class="odd gradeC" onclick="location.href = 'ControlePermissao?irPerfil=&idApp=${app.id}&ud=${usuarioId}';">
					                                            <td>${app.nome}</td>
				                                        </tr>
				                               		</c:forEach>         
			                                    </tbody>
			                                </table>
								    	</div>
								    	<div class="col-lg-6">
								    		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			                                    <thead>
			                                        <tr>
			                                        	<th></th>
			                                            <th>Perfil</th>	                                            
			                                        </tr>
			                                    </thead>
			                                    <tbody>
													<c:forEach items="${listarPefisNew}" var="per">
				                                        <tr class="odd gradeC">
					                                            <td><input type="checkbox" name="idper" value="${per.id}" ${per.check} /></td>
					                                            <td>${per.name}</td>
				                                        </tr>
				                               		</c:forEach>         
			                                    </tbody>
			                                </table>
			                                
			                                <div class="form-group text-right" style="margin-top:0px;">
		                                		<input type="submit" class="btn btn-default" name="btnCadastro" value="Cadastrar" />
		                                    </div>
								    	</div>
							    	</div>	
						    	</form>
						    </div>
						  </div>
						  <div id="menu1" class="tab-pane fade">
						    <form role="form1" name="form1" action="ControlePermissao" method="post">
<!-- 							    <div class="form-group"> -->
<!-- 	                                <label>Igrejas</label> -->
<!-- 	                                <select name="selecionarIgrejas" class="form-control" > -->
<!-- 	                               	<option selected></option> -->
<%-- 	                                	<c:forEach items="${listarIgrejas}" var="igr"> --%>
<%-- 	                                    	<option value="${igr.id}">${igr.name}</option> --%>
<%-- 	                                	</c:forEach> --%>
<!-- 	                                </select> -->
<!-- 	                                <input type="submit" class="btn btn-default" name="btnAdd" value="Cadastrar" /> -->
<!-- 	                            </div> -->
	                            <div class="from-group">
									<div class="col-lg-6">
										<div class="from-group">
								    		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			                                    <thead>
			                                        <tr>
			                                        	<th></th>
			                                            <th>Nome</th>	                                            
			                                        </tr>
			                                    </thead>
			                                    <tbody>
													<c:forEach items="${listarIgre}" var="igr">
				                                        <tr class="odd gradeC">
					                                            <td><input type="checkbox" name="idigr" value="${igr.id}" ${igr.check}/></td>
					                                            <td>${igr.name}</td>
				                                        </tr>
				                               		</c:forEach>         
			                                    </tbody>
			                                </table>
		                                </div>
			                            <div class="form-group text-right" style="margin-top:0px;">
			                       			<input type="submit" class="btn btn-default" name="btnAdd" value="Cadastrar" />
			                            </div>
		                            </div>
								</div>
                            </form>
						  </div>
						  <div id="menu2" class="tab-pane fade">
						    <h3>Menu 2</h3>
						    <p>Some content in menu 2.</p>
						  </div>
						</div>
			       </div>	    
			   </div>
	       </div>
       </div>
	</div>
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