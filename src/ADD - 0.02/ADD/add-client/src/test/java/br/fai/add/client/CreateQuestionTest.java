package br.fai.add.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateQuestionTest {

    WebDriver webDriver;

    //Professora, Jose aqui, vou descrever o passo-a-passo sem repetir descrições sobre o que ja foi feito em outros testes
    @org.junit.Before
    public void setUp() throws Exception {

        //Passo 1 - Setta o caminho do exe utilizado para os testes do Selenium
        System.setProperty("webdriver.chrome.driver", "D:/Programas/Selenium/chromedriver.exe");

        //Passo 2 - Abre o chrome
        webDriver = new ChromeDriver();

        //Passo 3 - Navega para a pagina local do nosso projeto
        webDriver.navigate().to("http://localhost:8080/");

        //Passo 4 - Procura o link de login
        webDriver.findElement(By.id("doLogin")).click();

        //Passo 5 - Faz login com username e senha de um administrador do sistema
        webDriver.findElement(By.id("username")).sendKeys("admin");

        webDriver.findElement(By.id("password")).sendKeys("admin");
        //Passo 6 - Clica no botão de login
        webDriver.findElement(By.id("botaoLogin")).click();
        //Passo 7 - Acessa o módulo de criar formulario
        webDriver.findElement(By.id("createForm_id")).click();

    }


    @org.junit.Test
    public void createQuestionOk_Test() {

        // Passo 1 - Acessa os detalhes do primeiro formulario encontrado
        webDriver.findElement(By.id("detailForm_id")).click();
        // Passo 2 - Clica no botao de adicionar pergunta ao formulário
        webDriver.findElement(By.id("addQuestion_id")).click();
        // Passo 3 - Digita "Pergunta unica 105" no campo de preencher pergunta
        webDriver.findElement(By.id("description_question_id")).sendKeys("Pergunta Unica 105");
        // Passo 4 - Clica no botao de submeter pergunta
        webDriver.findElement(By.id("submit_question_id")).click();

        // Passo 5 - Instancia uma lista de string e uma lista de objetos webelement que recebe todas as perguntas
        // Fiz assim pq o .getText nao funciona com o .isDisplayed
        List<String> perguntas = new ArrayList<>();
        List<WebElement> elementosWeb = webDriver.findElements(By.id("questionDescription_id"));
        //Passo 6 - Pega o texto dentro de cada elementoWeb e o copia para dentro da lista de string
        for (WebElement webelement : elementosWeb) {
            perguntas.add(webelement.getText());
        }

        // Passo 7 - Verifica se existe uma pergunta com o texto "Pergunta Unica 105" nos webelements contidos na tela
        // se sim, atribui true a uma variavel chamada perguntaExiste
        boolean perguntaExiste = false;
        for (String pergunta : perguntas) {
            if (pergunta.equals("Pergunta Unica 105")) {
                perguntaExiste = true;
            }
        }

        //Passo 8 - Checa se a variavel é verdadeira, se sim, passou no teste
        assertTrue(perguntaExiste);

        //Passo 9 - Fecha o chrome
        webDriver.quit();

    }


    @org.junit.Test
    public void createQuestion_WithNullDescription_mustNotCreate_Test() {

        webDriver.findElement(By.id("detailForm_id")).click();

        webDriver.findElement(By.id("addQuestion_id")).click();

        //Submete a pergunta sem digitar nada no campo de texto, portanto descricao nula
        webDriver.findElement(By.id("submit_question_id")).click();

        List<String> perguntas = new ArrayList<>();
        List<WebElement> elementosWeb = webDriver.findElements(By.id("questionDescription_id"));
        for (WebElement webelement : elementosWeb) {
            perguntas.add(webelement.getText());
        }

        //Verifica se existe uma pergunta com texto nulo
        boolean perguntaExiste = false;
        for (String pergunta : perguntas) {
            if (pergunta.equals("")) {
                perguntaExiste = true;
            }
        }

        //Se nao existir, a variavel perguntaexiste continua falsa, portanto assertFalse para garantir que o teste esta correto
        assertFalse(perguntaExiste);

        webDriver.quit();

    }

    @org.junit.Test
    public void createAlternativeQuestion_Ok_Test() {
        webDriver.findElement(By.id("detailForm_id")).click();

        webDriver.findElement(By.id("addQuestion_id")).click();

        webDriver.findElement(By.id("description_question_id")).sendKeys("Pergunta Unica 106");
        //Seleciona a caixinha que diz que a pergunta é de alternativa
        webDriver.findElement(By.id("alternative_question_id")).click();
        //Com a caixinha selecionada, o programa vai redirecionar para a pagina de criacao de alternativas
        webDriver.findElement(By.id("submit_question_id")).click();

        //Cria uma alternativa dentro da pergunta
        webDriver.findElement(By.id("alternativeDescription_id")).sendKeys("Alternativa Unica 101a");

        //Submete a alternativa
        webDriver.findElement(By.id("addAlternative_id")).click();

        //Volta a pagina de criar formulario
        webDriver.findElement(By.id("createForm_id")).click();

        //Volta a pagina do primeiro formulario listado
        webDriver.findElement(By.id("detailForm_id")).click();

        //Check 1/2 - Garante que uma pergunta com aquele nome existe
        List<String> perguntas = new ArrayList<>();
        List<WebElement> elementosWebPerguntas = webDriver.findElements(By.id("questionDescription_id"));
        for (WebElement webelement : elementosWebPerguntas) {
            perguntas.add(webelement.getText());
        }

        boolean perguntaExiste = false;
        for (String pergunta : perguntas) {
            if (pergunta.equals("Pergunta Unica 106")) {
                perguntaExiste = true;
            }
        }

        //Garante que a pergunta com alternativas existe
        assertTrue(perguntaExiste);

        //Fecha o chrome
        webDriver.quit();

        //Check 2/2 - Checa se alternativa existe
        //
        //Abre o chrome denovo e loga como usuario desta vez
        webDriver = new ChromeDriver();

        webDriver.navigate().to("http://localhost:8080/");

        webDriver.findElement(By.id("doLogin")).click();

        webDriver.findElement(By.id("username")).sendKeys("user");

        webDriver.findElement(By.id("password")).sendKeys("user");

        webDriver.findElement(By.id("botaoLogin")).click();

        //Acessa a pagina de responder o primeiro formulario
        webDriver.findElement(By.id("answer-form")).click();

        //Acessa a pagina de responder a primeira pergunta
        webDriver.findElement(By.id("answerQuestion_btn_id")).click();

        //Cria uma lista de webelements encontrados por classname que contem os texto das alternativas
        List<String> alternativas = new ArrayList<>();
        List<WebElement> elementosWebAlternativas = webDriver.findElements(By.className("form-check-label"));
        for (WebElement webelement : elementosWebAlternativas) {
            alternativas.add(webelement.getText());
        }

        //Apos instanciar uma outra listar com o texto das alternativas, checa se a alternativa criada esta
        // la dentro
        boolean alternativaExiste = false;
        for (String alternativa : alternativas) {
            if (alternativa.equals("Alternativa Unica 101a")) {
                alternativaExiste = true;
            }
        }

        //
        assertTrue(alternativaExiste);

        webDriver.quit();


    }

    @org.junit.Test
    public void createAlternativeQuestion_withNullAlternativeDescription_mustNotCreateAlternative_Test() {

        webDriver.findElement(By.id("detailForm_id")).click();

        webDriver.findElement(By.id("addQuestion_id")).click();

        webDriver.findElement(By.id("description_question_id")).sendKeys("Pergunta Unica 107");

        webDriver.findElement(By.id("alternative_question_id")).click();

        webDriver.findElement(By.id("submit_question_id")).click();

        //Manda uma descricao nula dentro da alternativa
        webDriver.findElement(By.id("alternativeDescription_id")).sendKeys("");

        webDriver.findElement(By.id("option_label")).sendKeys("b");

        webDriver.findElement(By.id("alternativeDescription_id")).sendKeys("");

        //Fecha o chrome
        webDriver.quit();

        //Abre o chrome denovo e loga como usuario desta vez
        webDriver = new ChromeDriver();

        webDriver.navigate().to("http://localhost:8080/");

        webDriver.findElement(By.id("doLogin")).click();

        webDriver.findElement(By.id("username")).sendKeys("user");

        webDriver.findElement(By.id("password")).sendKeys("user");

        webDriver.findElement(By.id("botaoLogin")).click();

        //Acessa a pagina de responder o primeiro formulario
        webDriver.findElement(By.id("answer-form")).click();

        //Acessa a pagina de responder a primeira pergunta
        webDriver.findElement(By.id("answerQuestion_btn_id")).click();

        //Cria uma lista de webelements encontrados por classname que contem os texto das alternativas
        List<String> alternativas = new ArrayList<>();
        List<WebElement> elementosWeb = webDriver.findElements(By.className("form-check-label"));
        for (WebElement webelement : elementosWeb) {
            alternativas.add(webelement.getText());
        }

        //Apos instanciar uma outra listar com o texto das alternativas, checa se a alternativa criada que era nula esta
        // la dentro
        boolean alternativaExiste = false;
        for (String alternativa : alternativas) {
            if (alternativa.equals("")) {
                alternativaExiste = true;
            }
        }

        //Como o sistema cancelou a criacao da alternativa por estar nula, o teste passa
        assertFalse(alternativaExiste);

        webDriver.quit();

    }

}
