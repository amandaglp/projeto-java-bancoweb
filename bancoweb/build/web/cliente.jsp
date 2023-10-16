<!DOCTYPE html>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="estilo.css"%></style>
    </head>
    <body>
        <div class="margem">


            <c:out value='${mensagem}'/>

            <div class="cabecalho">
                <h1>Cliente</h1>
            </div>
            <div class="container">
                <c:if test="${cliente == null}">
                    <form  action="inserir" method="post">
                    </c:if>
                    <c:if test="${cliente != null}">
                        <form  action="editar" method="post">
                            <input type="hidden" id="id" name="id" value="<c:out value='${cliente.id}'/>">
                        </c:if>

                        <div class="row">
                            <div class="col-25">
                                <label for="nome">Nome</label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="nome" name="nome" value="<c:out value='${cliente.nome}'/>">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label>Sexo</label>
                            </div>
                            <div class="col-75 boxBackground">
                                <input type="radio" id="masculino" name="sexo" value="M" ${cliente.sexo == 77 ? 'checked':''}/>
                                <label for="masculino">Masculino</label><br>
                                <input type="radio" id="feminino" name="sexo" value="F"${cliente.sexo == 70 ? 'checked':''}/>
                                <label for="feminino">Feminino</label><br>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="email">Email</label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="email" name="email" value="<c:out value='${cliente.email}'/>">

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label>Estado Civil</label>
                            </div>
                            <div class="col-75 boxBackground">
                                <input type="radio" id="solteiro" name="civil" value="Solteiro(a)"${cliente.civil == 'Solteiro(a)' ? 'checked':''}/>
                                <label for="Solteiro(a)">Solteiro(a)</label><br>
                                <input type="radio" id="casado" name="civil" value="Casado(a)"${cliente.civil == 'Casado(a)' ? 'checked':''}/>
                                <label for="Casado(a)">Casado(a)</label><br>
                                <input type="radio" id="divorciado" name="civil" value="Divorciado(a)"${cliente.civil == 'Divorciado(a)' ? 'checked':''}/>
                                <label for="Divorciado(a)">Divorciado(a)</label><br>
                                <input type="radio" id="viúvo" name="civil" value="Viúvo(a)"${cliente.civil == 'Viúvo(a)' ? 'checked':''}/>
                                <label for="Viúvo(a)">Viúvo(a)</label><br>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="regiao">Região</label>
                            </div>
                            <div class="col-75">
                                <label for="regiao">Escolha uma região:</label>

                                <select id="regiao" name="regiao">
                                    
                                    <option value="Norte" ${cliente.regiao == 'Norte' ? 'selected':''}>Norte</option>
                                    <option value="Nordeste" ${cliente.regiao == 'Nordeste' ? 'selected':''}>Nordeste</option>
                                    <option value="Sul" ${cliente.regiao == 'Sul' ? 'selected':''}>Sul</option>
                                    <option value="Sudeste" ${cliente.regiao == 'Sudeste' ? 'selected':''}>Sudeste</option>
                                    <option value="Centro-oeste" ${cliente.regiao == 'Centro-Oeste' ? 'selected':''}>Centro-Oeste</option>
                                </select>

                            </div>



                            <div class="row">
                                <div class="col-100">
                                    <input type="submit" value="Salvar">
                                </div>
                            </div>
                    </form>
            </div>            
        </div>
        <div style="text-align: center;">
            <a href="listar">Listar Clientes</a>
        </div>
    </body>
</html>
