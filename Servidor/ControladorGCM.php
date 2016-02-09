<?php

class ControladorGCM{
    private $headers;

    public function __construct(){
        $this->headers =
            [
                'Authorization: key=' . "AIzaSyAV5XeN3A1MsMPMUn-nSKKzYDlmd8i2B5s",
                'Content-Type: application/json'
            ];
    }

    private function enviarPreparado($data){
        $ch = curl_init();

        curl_setopt($ch, CURLOPT_URL, 'https://android.googleapis.com/gcm/send');

        if ($this->headers)
            curl_setopt($ch, CURLOPT_HTTPHEADER, $this->headers);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);

        $response = curl_exec($ch);
        var_dump($response);

        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        if (curl_errno($ch)) {
            return false;
        }
        if ($httpCode != 200) {
            return false;
        }

        curl_close($ch);

        return $response;
    }

    public function enviarDadoParaTelefone($deviceToken, array $data) {
        $gcm = [
            'registration_ids' => $deviceToken,
            'data' => $data];
        return $this->enviarPreparado(json_encode($gcm));
    }

    public function enviarDadoParaTopico($topico, array $data) {
        $gcm = [
            'to' => $topico,
            'data' => $data
        ];
        return $this->enviarPreparado(json_encode($gcm));
    }
}