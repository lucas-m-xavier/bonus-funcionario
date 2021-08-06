package br.ufes.projetos01;

import br.ufes.calculodebonus.ProcessadoraBonus;
import br.ufes.model.Funcionario;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.rules.ExpectedException;

/**
 *
 * @author clayton
 * https://docs.google.com/spreadsheets/d/13XJfo39VUdoURp59KxY00zkkHiAwoGh0H5vzFq7OsJ0/edit?usp=sharing
 */
public class FuncionarioBonusTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public FuncionarioBonusTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void CT001() throws Exception{
        Funcionario funcionario = new Funcionario("Dominique", 1000.00, "Programador");
        funcionario.setDistanciaMoradia(213);
        funcionario.setFaltas(0);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = 1600.00;
        assertEquals(salarioEsperado, funcionario.getSalario(), 0.001);
    }

    @Test
    public void CT002() throws Exception{

        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#3 O salário base deve ser >= R$ 998,00"));
        Funcionario funcionario = new Funcionario("Célio", 800.00, "GERENTE");
    }

    @Test
    public void CT003() throws Exception{

        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#1 Informe um nome válido"));
        Funcionario funcionario = new Funcionario(null, 2000.00, "SUPERVISOR");
    }

    @Test
    public void CT004() throws Exception{

        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#2 Informe um cargo válido"));
        Funcionario funcionario = new Funcionario("Hélio", 3000.00, null);
    }

    @Test
    public void CT005() throws Exception{
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#4 Informe uma distância válida"));
        Funcionario funcionario = new Funcionario("Celina", 1300.00, "GERENTE");
        funcionario.setDistanciaMoradia(-99);
    }

    @Test
    public void CT006() throws Exception{
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#5 Informe um número de faltas válido"));
        Funcionario funcionario = new Funcionario("Cauã", 3200.00, "SUPERVISOR");
        funcionario.setFaltas(-20);
    }

    @Test
    public void CT007() throws Exception{
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#1 Informe um nome válido"));
        Funcionario funcionario = new Funcionario("Teresa77", 1900.00, "GERENTE");
    }

    @Test
    public void CT008() throws Exception{

        Funcionario funcionario = new Funcionario("Sara", 1000.00, "PROGRAMADOR");

        funcionario.setFaltas(0);
        funcionario.setDistanciaMoradia(11);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = 1100.00;
        assertEquals(salarioEsperado, funcionario.getSalario(), 0.001);
    }
}