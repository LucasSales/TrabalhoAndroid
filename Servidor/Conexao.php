<?php
/**
 * Created by PhpStorm.
 * User: loopback
 * Date: 19/01/16
 * Time: 23:52
 */

class Conexao{
    public static $conexao;

    public static function getConexao(){
        if(Conexao::$conexao!=null)
            return Conexao::$conexao;
        else
            return new PDO('mysql:host=localhost;dbname=msg', 'root',
                '123', array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
    }

}