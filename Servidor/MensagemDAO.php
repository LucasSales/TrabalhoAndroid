<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 27/01/16
 * Time: 03:11
 */
include_once 'Mensagem.php';
class MensagemDAO{

    private $conexao;

    public function __construct($conexao){
        $this->conexao = $conexao;
    }

    public function cadastrar(Mensagem $msg,$valor){
        try {
            $sql = "insert into mensagem( idFrom,idTo,mensagem, id_loc, visto) values (?,?,?,?,?)";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1, $msg->getToken());
            $a->bindParam(2, $msg->getIdTo());
            $a->bindParam(3, $msg->getMensagem());
            $a->bindParam(4, $msg->getIdLoc());
            $a->bindParam(5, $valor);

            return $a->execute();

        }catch (Exception $e) {
            return false;
        }
    }

    public function buscar(){
        try {
            $sql = "select * from mensagem";

            $a = $this->conexao->prepare($sql);

            if(!$a->execute())
                return false;

            $resultadoRetorno = array();

            while ($resultado = $a->fetchObject())
                array_push($resultadoRetorno, $resultado);

            return $resultadoRetorno;
        }catch (Exception $e){
            return false;
        }
    }

    public function buscaMsgLocalizacao($ids,$id){
        try {
            $idsFormatados = implode(',',$ids);

            $sql = "select * from mensagem where id_loc in (".$idsFormatados.") and idTo = ?";

            $a = $this->conexao->prepare($sql);

            //$a->bindParam(1, $idsFormatados);
            $a->bindParam(1, $id);


            if(!$a->execute()) {
                //var_dump($a->errorInfo());
                return false;
            }
            $resultadoRetorno = array();

            while ($resultado = $a->fetchObject())
                array_push($resultadoRetorno, $resultado->mensagem);

            return $resultadoRetorno;
        }catch (Exception $e){
            return false;
        }
    }
    public function buscaMsgIdsLoc($ids,$id){
        try {
            $idsFormatados = implode(',',$ids);

            $sql = "select * from mensagem where id_loc in (".$idsFormatados.") and idTo = ?";

            $a = $this->conexao->prepare($sql);

            //$a->bindParam(1, $idsFormatados);
            $a->bindParam(1, $id);


            if(!$a->execute()) {
                //var_dump($a->errorInfo());
                return false;
            }
            $resultadoRetorno = array();

            while ($resultado = $a->fetchObject())
                array_push($resultadoRetorno, $resultado->mensagem);

            return $resultadoRetorno;
        }catch (Exception $e){
            return false;
        }
    }


    public function visto($id){
        try {
            $idsFormatados = implode(',',$id);
            $sql = "insert into mensagem (visto) values (1) where id in '.$idsFormatados.'";

            $a = $this->conexao->prepare($sql);

            if(!$a->execute())
                return var_dump($a->errorInfo());


        }catch (Exception $e){
            return false;
        }
    }
}