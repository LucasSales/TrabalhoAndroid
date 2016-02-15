<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 14/02/16
 * Time: 23:51
 */
include_once "ControladorUser.php";

if(isset($_POST['metodo'])){

    $controlador = ControladorUser::getInstance();
    $jsonObject = json_decode($_POST['metodo']);

    $registrationId = $jsonObject->registrationId;

    //echo json_encode(array('pessoas'=>$controlador->buscarNick($nick)));
    echo json_encode($controlador->deletarUsuario($registrationId));



}else
    echo json_encode("os parametros não foram passados corretamente");