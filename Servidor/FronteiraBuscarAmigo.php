<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 27/01/16
 * Time: 23:35
 */
include_once "ControladorUser.php";

if(isset($_POST['jsonObject'])){

    $controlador = ControladorUser::getInstance();
    $jsonObject = json_decode($_POST['jsonObject']);
    $id = $jsonObject->amigo->idAmigo;
    echo json_encode(array('pessoas'=>$controlador->buscar(2)));

}else
    echo json_encode("os parametros não foram passados corretamente");
