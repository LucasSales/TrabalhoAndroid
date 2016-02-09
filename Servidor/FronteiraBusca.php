<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 28/01/16
 * Time: 19:29
 */
include_once "ControladorUser.php";

if(isset($_POST['metodo'])){

    $controlador = ControladorUser::getInstance();
    $jsonObject = json_decode($_POST['metodo']);

    $nick = $jsonObject->nick;

    //echo json_encode(array('pessoas'=>$controlador->buscarNick($nick)));
    echo json_encode($controlador->buscarNick($nick));



}else
    echo json_encode("os parametros não foram passados corretamente");