package pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Testing00 {
    private WebDriver myDriver;
    private WebDriverWait myDriverWait;
    private String userDir = System.getProperty("user.dir");
    private String rutaDriver = "/src/main/resources/drivers/chromedriver.exe";
    private String driverPath = userDir + rutaDriver;


    // EN ALGUNOS TESTS USO SLEEP A PROPÓSITO PARA HACER LOS CAMBIOS MÁS VISIBLES PARA EL OJO HUMANO ;)

    // Test data
    // fuente: https://generarut.cl/
    private String nombreCliente = "Juan Perez";
    private String rutCorrecto = "22.599.935-k";
    private String emailCorrecto = "ejemplo@gmail.com";
    private String numeroFormatoCorrecto = "363148440";

    private String resultadoEsperado = "";
    private String resultado = "";


    @BeforeSuite
    public static void preparaSuiteDePruebas() {
        System.out.println("\nIniciando Suit de pruebas...");

    }

    @BeforeClass
    public void prepararClase() {
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    @BeforeMethod
    public void prepararTests() {
        myDriver = new ChromeDriver();

        myDriverWait = new WebDriverWait(myDriver, 10);
        myDriver.manage().window().maximize();
        myDriver.get("https://www.banmedica.cl/");
    }

    @AfterMethod
    public void closeBrowser() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        myDriver.close();
    }

    @Test
    public void testInputDatosCorrectos() {
        /*
        ATENCION::::::::::
        SI AL MOMENTO DE ENVIAR EL FORMULARIO EL RUT DA ERROR, ¡¡USAR UNO NUEVO!!!
        (AL COMIENZO DE LA CLASE ESTÁ EL LINK A LA PAGINA WEB DONDE GENERAR UN RUT VÁLIDO). */
        System.out.println("\nTesting envío de formulario con datos correctos...\n");
        myDriver.findElement(By.cssSelector("a.invitacion")).click();


        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.id("name")).sendKeys(nombreCliente);
        myDriver.findElement(By.id("rut")).sendKeys(rutCorrecto);
        myDriver.findElement(By.id("email")).sendKeys(emailCorrecto);
        myDriver.findElement(By.id("fono")).sendKeys(numeroFormatoCorrecto);
        myDriver.findElement(By.id("glosa")).sendKeys("Mañana va a ser un gran día, te lo" +
                " digo yo ;) -letra de una canción- ");
        Select menuRegiones = new Select(myDriver.findElement(By.id("regiones")));
        menuRegiones.selectByVisibleText("REGION Metropolitana");

        Select menuComunas = new Select(myDriver.findElement(By.id("comuna")));
        menuComunas.selectByVisibleText("Estacion Central");


        myDriver.findElement(By.id("enviar")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }


        String resultadoEsperado = "Tus datos han sido enviados, un ejecutivo de Banmédica se pondrá en contacto en las siguientes 24 horas.";
        String resultado = myDriver.findElement(By.xpath("//*[@id=\"datas_modal\"]/div/div/div/div/div[2]/h5/p")).getText();
        // TEST
        System.out.println("RESULTADO: " + resultado + "\n");


        Assert.assertEquals(resultado, resultadoEsperado);

    }

    @Test
    public void testPreservacionDatosIngresados() {
        System.out.println("\nTesting preservación de datos ingresados...\n");
        myDriver.findElement(By.cssSelector("a.invitacion")).click();

        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.id("name")).sendKeys(nombreCliente);
        myDriver.findElement(By.id("rut")).sendKeys(rutCorrecto);
        myDriver.findElement(By.id("email")).sendKeys(emailCorrecto);
        myDriver.findElement(By.id("fono")).sendKeys(numeroFormatoCorrecto);
        myDriver.findElement(By.id("glosa")).sendKeys("Mañana va a ser un gran día, te lo" +
                " digo yo ;) -letra de una canción- ");
        Select menuRegiones = new Select(myDriver.findElement(By.id("regiones")));
        menuRegiones.selectByVisibleText("REGION Metropolitana");

        Select menuComunas = new Select(myDriver.findElement(By.id("comuna")));
        menuComunas.selectByVisibleText("Estacion Central");


        // Cerramos dialogo
        myDriver.findElement(By.xpath("//*[@id=\"datas_modal\"]/div/div/div/div/div[1]/img")).click();

        // Click en "mi cuenta"
        myDriver.findElement(By.xpath("//*[@id=\"menu-item-723\"]/a")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        // Volvemos atrás
        myDriver.navigate().back();


        // Reabrimos dialogo
        myDriver.findElement(By.cssSelector("a.invitacion")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }


        resultadoEsperado = nombreCliente;
        resultado = myDriver.findElement(By.xpath("//*[@id=\"name\"]")).getAttribute("value");

        Assert.assertEquals(resultado, resultadoEsperado);

    }


    @Test
    public void testEnvioFormularioVacio() {
        System.out.println("\nTesting envío de formulario vacío...\n");
        myDriver.findElement(By.cssSelector("a.invitacion")).click();

        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.id("enviar")).click();


        resultadoEsperado = "Ingresa tu RUT";
        resultado = myDriver.findElement(By.xpath("//*[@id=\"rut-error\"]")).getText();
        // TEST
        System.out.println("RESULTADO: " + resultado + "\n");

        Assert.assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void testRedireccionUrl() {
        System.out.println("\nTesting redirección de URL...\n");
        // Click en "Sucursales"
        myDriver.findElement(By.xpath("//*[@id=\"menu-item-284\"]/a")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        resultadoEsperado = "https://www.banmedica.cl/sucursales/";
        resultado = myDriver.getCurrentUrl();

        // TEST
        System.out.println("URL ESPERADA: " + resultadoEsperado);
        System.out.println("URL ACTUAL/RESULTADO: " + resultado + "\n");

        Assert.assertEquals(resultado, resultadoEsperado);
    }


    @Test
    public void ingresoFolioPagoErroneo() {
        myDriver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div/ul[1]/li[2]/a/span")).click();

        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.id("numero_folio")).sendKeys("999999999999");


        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.xpath("//*[@id=\"submitfolio\"]")).click();


        Pattern pattern = Pattern.compile("No se encontro el número de Folio");
        myDriverWait.until(ExpectedConditions.textMatches(By.cssSelector("[role='alert']"), pattern));


        resultadoEsperado = "No se encontro el número de Folio";
        resultado = myDriver.findElement(By.cssSelector("[role='alert']")).getText();

        // TEST
        System.out.println("\nRESULTADO: " + resultado);

        Assert.assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void testInputDatosEmailErroneo() {

        System.out.println("\nTesting envío de formulario con email erróneo...\n");
        myDriver.findElement(By.cssSelector("a.invitacion")).click();


        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.id("name")).sendKeys(nombreCliente);
        myDriver.findElement(By.id("rut")).sendKeys(rutCorrecto);
        myDriver.findElement(By.id("email")).sendKeys("ejemplo.com");
        myDriver.findElement(By.id("fono")).sendKeys(numeroFormatoCorrecto);

        Select menuRegiones = new Select(myDriver.findElement(By.id("regiones")));
        menuRegiones.selectByVisibleText("REGION Metropolitana");

        Select menuComunas = new Select(myDriver.findElement(By.id("comuna")));
        menuComunas.selectByVisibleText("Estacion Central");


          myDriver.findElement(By.id("enviar")).click();


        myDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-error")));


        String resultadoEsperado = "Ingresa un email";
        String resultado = myDriver.findElement(By.id("email-error")).getText();
        // TEST
        System.out.println("RESULTADO: " + resultado + "\n");


           Assert.assertEquals(resultado, resultadoEsperado);

    }

    @Test
    public void testInputDatosNumeroErroneo() {
        /*
        ATENCION::::::::::
        SI AL MOMENTO DE ENVIAR EL FORMULARIO EL RUT DA ERROR, ¡¡USAR UNO NUEVO!!!
        (AL COMIENZO DE LA CLASE ESTÁ EL LINK A LA PAGINA WEB DONDE GENERAR UN RUT VÁLIDO). */
        System.out.println("\nTesting envío de formulario con número erróneo...\n");
        myDriver.findElement(By.cssSelector("a.invitacion")).click();


        myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        myDriver.findElement(By.id("name")).sendKeys(nombreCliente);
        myDriver.findElement(By.id("rut")).sendKeys(rutCorrecto);
        myDriver.findElement(By.id("email")).sendKeys(emailCorrecto);
        myDriver.findElement(By.id("fono")).sendKeys("XYZ");

        Select menuRegiones = new Select(myDriver.findElement(By.id("regiones")));
        menuRegiones.selectByVisibleText("REGION Metropolitana");

        Select menuComunas = new Select(myDriver.findElement(By.id("comuna")));
        menuComunas.selectByVisibleText("Estacion Central");


           myDriver.findElement(By.id("enviar")).click();


        myDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fono-error")));

        String resultadoEsperado = "El número es demasiado corto, ingrese al menos 8 caractéres";
        String resultado = myDriver.findElement(By.id("fono-error")).getText();
        // TEST
        System.out.println("RESULTADO: " + resultado + "\n");


           Assert.assertEquals(resultado, resultadoEsperado);

    }


    @AfterTest
    public void quitBrowser() {
        myDriver.quit();
    }

}

