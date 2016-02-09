<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 29/01/16
 * Time: 04:25
 */

include_once 'LocalDAO.php';
include_once 'Conexao.php';

class ControleLocal{

    private $daoLoc;

    public function __construct(){

        $this->daoLoc = new LocalDAO(Conexao::getConexao());
    }
    public function buscar($lat, $long, $distancia){

        return $this->daoLoc->buscar($lat, $long, $distancia);
    }

}