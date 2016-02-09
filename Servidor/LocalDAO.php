<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 29/01/16
 * Time: 02:37
 */
include_once 'Conexao.php';
include_once 'Local.php';
class LocalDAO{
    private $conexao;

    public function __construct($conexao){
        $this->conexao = $conexao;
    }

    public function cadastrar(Local $local){

        try {
            $point = 'POINT('.$local->getLatitude().' '.$local->getLongitude().')';
            $sql =
                "INSERT INTO localizacao (coordenadas)
                VALUES (GeomFromText(?))";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1, $point);
            //$a->bindParam(2, $local->getLongitude());


            if($a->execute())
                return  $id = $this->conexao->lastInsertId();

            return false;
        }catch (Exception $e) {
            return false;
        }
    }

    public function buscarUsuarioToken(Local $local){
        try{
            $sql = "SELECT x(localizacao.coordenadas) AS latitude, y(localizacao.coordenadas) AS longitude FROM localizacao where id = ?";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1,$local->getId());

            if(!$a->execute())
                return false;

            $arrayalunos = array();

            while($aluno = $a->fetchObject())
                array_push($arrayalunos,$aluno);

            return $arrayalunos;

        }catch(Exception $e){
            return false;
        }
    }


    public function buscar($lat, $long, $distancia){
        try {
            $sql = "SELECT id, (6371 * acos(cos(radians(?)) * cos(radians(x(localizacao.coordenadas)))
                    * cos(radians(y(localizacao.coordenadas)) - radians(?)) + sin(radians(?))
                    * sin(radians(x(localizacao.coordenadas))))) AS distance
                    FROM localizacao
                    HAVING distance < ?
                    ORDER BY id
            ;";

            $a = $this->conexao->prepare($sql);
            $a->bindParam(1, $lat);
            $a->bindParam(2, $long);
            $a->bindParam(3, $lat);
            $a->bindParam(4, $distancia);

            if(!$a->execute())
                return false;

            $resultadoRetorno = array();

            while ($resultado = $a->fetchObject())
                array_push($resultadoRetorno, $resultado->id);

            //var_dump($resultadoRetorno);

            return $resultadoRetorno;
        }catch (Exception $e){
            return false;
        }
    }


}