package com.brdev.dio;

public class IPhone implements Phone, MusicPlayer, WebBrowser{
    @Override
    public void selectMusic(String music) {
        System.out.printf("Selecionando a música %s\n", music);
    }

    @Override
    public void makeCall(String number) {
        System.out.printf("Ligando para o número %s\n", number);
    }

    @Override
    public void answerCall() {
        System.out.println("Recebendo ligação...");
    }

    @Override
    public void initVoiceMail() {
        System.out.println("Iniciando correio de voz");
    }

    @Override
    public void play() {
        System.out.println("Reproduzindo música");
    }

    @Override
    public void pause() {
        System.out.println("Pausando música");
    }

    @Override
    public void stop() {
        System.out.println("Parando música");
    }

    @Override
    public void showPage(String url) {
        System.out.printf("Navegando para %s\n", url);
    }

    @Override
    public void addPage() {
        System.out.println("Nova página em branco");
    }

    @Override
    public void refreshPage() {
        System.out.println("Atualizando página");
    }
}
