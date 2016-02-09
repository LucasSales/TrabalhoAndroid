<?php
/**
 * Created by PhpStorm.
 * User: loopback
 * Date: 19/01/16
 * Time: 23:41
 */

class Mensagem{
    private $mensagem;
    private $token;
    private $idTo;
    private $id_loc;
    private $visto;

    /**
     * @return mixed
     */
    public function getVisto()
    {
        return $this->visto;
    }

    /**
     * @param mixed $visto
     */
    public function setVisto($visto)
    {
        $this->visto = $visto;
    }


    /**
     * @return mixed
     */
    public function getToken()
    {
        return $this->token;
    }

    /**
     * @param mixed $token
     */
    public function setToken($token)
    {
        $this->token = $token;
    }

    /**
     * @return mixed
     */
    public function getIdTo()
    {
        return $this->idTo;
    }

    /**
     * @param mixed $idTo
     */
    public function setIdTo($idTo)
    {
        $this->idTo = $idTo;
    }

    /**
     * @return mixed
     */
    public function getIdLoc()
    {
        return $this->id_loc;
    }

    /**
     * @param mixed $id_loc
     */
    public function setIdLoc($id_loc)
    {
        $this->id_loc = $id_loc;
    }


    /**
     * @return mixed
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * @param mixed $latitude
     */
    public function setLatitude($latitude)
    {
        $this->latitude = $latitude;
    }

    /**
     * @return mixed
     */
    public function getLogitude()
    {
        return $this->logitude;
    }

    /**
     * @param mixed $logitude
     */
    public function setLogitude($logitude)
    {
        $this->logitude = $logitude;
    }


    public function getMensagem()
    {
        return $this->mensagem;
    }

    /**
     * @param mixed $mensagem
     */
    public function setMensagem($mensagem)
    {
        $this->mensagem = $mensagem;
    }


    /**
     * @return mixed
     */
    public function getRegistroId()
    {
        return $this->registro_id;
    }

    /**
     * @param mixed $registro_id
     */
    public function setRegistroId($registro_id)
    {
        $this->registro_id = $registro_id;
    }





}