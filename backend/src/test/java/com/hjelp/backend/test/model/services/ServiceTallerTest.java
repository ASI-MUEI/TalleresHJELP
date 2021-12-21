package com.hjelp.backend.test.model.services;

import com.hjelp.backend.model.daos.*;
import com.hjelp.backend.model.entities.*;
import com.hjelp.backend.model.exceptions.*;
import com.hjelp.backend.model.services.Block;
import com.hjelp.backend.model.services.ServicioTaller;
import com.hjelp.backend.rest.conversor.AsistenciaConversor;
import com.hjelp.backend.rest.dtos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ServiceTallerTest {

    @Autowired
    ServicioTaller servicioTaller;

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    MarcaDao marcaDao;

    @Autowired
    ModeloDao modeloDao;

    @Autowired
    VehiculoDao vehiculoDao;

    @Autowired
    PiezaDao piezaDao;

    @Autowired
    TrabajoDao trabajoDao;

    @Autowired
    EstadoTrabajosDao estadoTrabajosDao;

    @Autowired
    AsistenciasDao asistenciasDao;

    @Autowired
    PuestoTallerDao puestoTallerDao;

    @Autowired
    TipoAsistenciasDao tipoAsistenciasDao;

    @Autowired
    HorariosDao horariosDao;

    /******** Métodos para los test *********************/

    private Usuario registrarUsuario() {
        Usuario user = new Usuario();
        user.setNombreUsuario("Laura");
        user.setApellidosUsuario("Insua Regueiro");
        user.setCorreoElectronicoUsuario("laura@gmail.com");
        user.setDni("48177777V");
        user.setNombrePilaUsuario("laura");
        user.setContrasenaUsuario("123123");
        user = usuarioDao.save(user);
        return user;
    }

    private Usuario registrarUsuario2() {
        Usuario user = new Usuario();
        user.setNombreUsuario("Lauri");
        user.setApellidosUsuario("Insua Regueiro");
        user.setCorreoElectronicoUsuario("lauri@gmail.com");
        user.setDni("4817777V");
        user.setNombrePilaUsuario("laura");
        user.setContrasenaUsuario("123123");
        user = usuarioDao.save(user);
        return user;
    }

    private Marca registrarMarca(){
        return marcaDao.save(new Marca(1L,"Ford", "Descripcion Ford"));
    }

    private Modelo registrarModelo(Marca marca){
        return modeloDao.save(new Modelo(1L, "Ford focus", "Descripcion Ford",marca, ""));
    }

    private void registrarPiezas(){
        piezaDao.save(new Pieza("Tubo de escape (BMW M Performance)", "Tubo de escape descripcion", "https://rparts-sites.s3.amazonaws.com/c9da6b63e26b3287a6a68fe8ee3a4330/design/installation%20instructions/F2x%20F3x%20B58%20M%20Performance%20Exhaust%20Installation%20Intructions.PDF", 5000));
        piezaDao.save(new Pieza("Michelin Pilot Sport 4s ", "Neumático 235/35 R19", "https://dcadprod.azureedge.net/b2c-experience-production/attachments/ckcdgp9qq0bnn01pgdqgtangn-ckbs97dw00oiy01o2e0yru8ux-manual-usuario-digital-michelin.pdf", 125));
        piezaDao.save(new Pieza("Electrónica grupo VAG ", "Revisión electrónica", "https://www.andinos.com.pe/wp-content/uploads/2019/05/MANUAL-DE-FALLAS-AUDI-VOLKSWAGEN-SEAT-2.pdf", 500));
    }

    private void registrarInfoPrimera(){
        registrarModelo(registrarMarca());
        registrarTiposAsistencias();
        registrarEstadosTrabajo();
        registrarPuestos();
        registrarUsuario();
        registrarHorarios();
        registrarPiezas();
    }

    private Vehiculo registrarVehiculo(Usuario user, Modelo modelo) {
        Vehiculo vehiculo = new Vehiculo();;
        vehiculo.setMatricula("6564GMP");
        vehiculo.setUsuario(user);
        vehiculo.setNumBastidor("542254254");
        vehiculo.setModelo(modelo);
        return vehiculoDao.save(vehiculo);
    }

    private Vehiculo registrarVehiculo2(Usuario user, Modelo modelo) {
        Vehiculo vehiculo = new Vehiculo();;
        vehiculo.setMatricula("6564GMT");
        vehiculo.setUsuario(user);
        vehiculo.setNumBastidor("54254254");
        vehiculo.setModelo(modelo);
        return vehiculoDao.save(vehiculo);
    }

    private void registrarEstadosTrabajo(){
        estadoTrabajosDao.save(new EstadoTrabajo("Abierto", "Estado trabajo abierto"));
        estadoTrabajosDao.save(new EstadoTrabajo("Cerrado", "Estado trabajo cerrado"));
        estadoTrabajosDao.save(new EstadoTrabajo("Pagado", "Estado trabajo pagado"));
    }

    private Trabajo registrarTrabajo(Vehiculo vehiculo, LocalDateTime fecha){
        Trabajo trabajo = new Trabajo();
        trabajo.setDescripcion("Nuevo trabajo test");
        trabajo.setEstado(estadoTrabajosDao.findByNombre("Abierto").get());
        trabajo.setNombre("Nombre trabajo test");
        trabajo.setVehiculo(vehiculo);
        if (fecha != null)
            trabajo.setFechaCreado(fecha);
        else trabajo.setFechaCreado(LocalDateTime.now());
        trabajo.setPeritado(false);
        return trabajoDao.save(trabajo);
    }

    private void registrarTiposAsistencias(){
        tipoAsistenciasDao.save(new TipoAsistencias("Aceite", ""));
        tipoAsistenciasDao.save(new TipoAsistencias("Reparación", ""));
        tipoAsistenciasDao.save(new TipoAsistencias("Neumáticos", ""));
    }

    private void registrarPuestos(){
        puestoTallerDao.save(new PuestoTaller(1L, "Elevador 1", "Puesto 1"));
        puestoTallerDao.save(new PuestoTaller(2L, "Elevador 2", "Puesto 2"));
        puestoTallerDao.save(new PuestoTaller(3L, "Elevador 3", "Puesto 3"));
        puestoTallerDao.save(new PuestoTaller(4L, "Elevador 4", "Puesto 4"));
        puestoTallerDao.save(new PuestoTaller(5L, "Elevador 5", "Puesto 5"));
    }

    private void registrarHorarios(){
        horariosDao.save(new Horarios("8:30-9:00"));
        horariosDao.save(new Horarios("9:00-9:30"));
        horariosDao.save(new Horarios("9:30-10:00"));
        horariosDao.save(new Horarios("10:00-10:30"));
        horariosDao.save(new Horarios("10:30-11:00"));
        horariosDao.save(new Horarios("11:00-11:30"));
        horariosDao.save(new Horarios("11:30-12:00"));
        horariosDao.save(new Horarios("12:00-12:30"));
        horariosDao.save(new Horarios("12:30-13:00"));
        horariosDao.save(new Horarios("13:00-13:30"));
        horariosDao.save(new Horarios("15:30-16:00"));
        horariosDao.save(new Horarios("16:00-16:30"));
        horariosDao.save(new Horarios("16:30-17:00"));
        horariosDao.save(new Horarios("17:00-17:30"));
        horariosDao.save(new Horarios("17:30-18:00"));
        horariosDao.save(new Horarios("18:00-18:30"));
        horariosDao.save(new Horarios("18:30-19:00"));
        horariosDao.save(new Horarios("19:00-19:30"));
        horariosDao.save(new Horarios("19:30-20:00"));
        horariosDao.save(new Horarios("20:00-20:30"));
    }

    private Asistencia registrarAsistencia(String descripcion, Trabajo trabajo, LocalDateTime fecha, Usuario usuario){
        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(fecha);
        asistencia.setPuesto(puestoTallerDao.findByNombre("Elevador 1").get());
        asistencia.setPrecio(20.0F);
        asistencia.setDescripcion(descripcion);
        asistencia.setPeritaje(false);
        asistencia.setDuracionEstimada(20L);
        asistencia.setRetrasada(false);
        asistencia.setTipo(tipoAsistenciasDao.findByNombre("Aceite").get());
        asistencia.setTrabajo(trabajo);
        List<Horarios> horarios = new ArrayList<>();
        horarios.add(horariosDao.findByNombre("10:00-10:30").get());
        asistencia.setHorarios(horarios);
        if (usuario != null){
            List<Usuario> mecanicos = new ArrayList<>();
            mecanicos.add(usuario);
            asistencia.setMecanicos(mecanicos);
        }
        asistencia.setPiezas(new ArrayList<>());
        return asistenciasDao.save(asistencia);
    }

    /********************************************************************/


    /*** US05 , T1.0*/
    @Test
    public void findAllAsistenciasTest() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.now(), null);
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.now(), null);
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.now(), null);

        Block<Asistencia> asistencias = servicioTaller.findAllAsistencias(0,5);
        List<Asistencia> asistenciasLista = asistencias.getItems();
        assertEquals(3, asistenciasLista.size());
        assertEquals(asistencia1.getDescripcion(), asistenciasLista.get(0).getDescripcion());
        assertEquals(asistencia2.getDescripcion(), asistenciasLista.get(1).getDescripcion());
        assertEquals(asistencia3.getDescripcion(), asistenciasLista.get(2).getDescripcion());

    }

    /*** US05 , T1.1*/
    @Test
    public void findAllAsistenciasOsizeTest(){
        Block<Asistencia> asistencias = servicioTaller.findAllAsistencias(0,5);
        List<Asistencia> asistenciasLista = asistencias.getItems();
        assertEquals(0, asistenciasLista.size());
    }

    /*** US05  , T2.0*/
    @Test
    public void getTrabajosAbiertosTest() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);

        assertEquals(1, servicioTaller.getTrabajosAbiertos().getContent().size());
    }

    /*** US05  , T3.0*/
    @Test
    public void getTipoAsitenciasTest() {
        registrarTiposAsistencias();
        List<TipoAsistencias> asistencias = servicioTaller.getTipoAsitencias();

        assertEquals(3, asistencias.size());
        assertEquals("Aceite", asistencias.get(0).getNombre());
        assertEquals("Reparación", asistencias.get(1).getNombre());
        assertEquals("Neumáticos", asistencias.get(2).getNombre());
    }

    /*** US05, US40 , T4.0*/
    @Test
    public void findAllAsistenciasPorFechaTest() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), null);
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.of(2021, 11, 10, 14, 15, 0, 0), null);
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.of(2021, 11, 10, 11, 15, 0, 0), null);

        Block<Asistencia> asistencias = servicioTaller.findAllAsistencias(0,5);
        List<Asistencia> asistenciasLista = asistencias.getItems();
        assertEquals(3, asistenciasLista.size());
        assertEquals(asistencia1.getDescripcion(), asistenciasLista.get(0).getDescripcion());
        assertEquals(asistencia2.getDescripcion(), asistenciasLista.get(1).getDescripcion());
        assertEquals(asistencia3.getDescripcion(), asistenciasLista.get(2).getDescripcion());
    }

    /*** US05, US29, US35  , T5.0*/
    @Test
    public void getAsistenciasOrderByFechaTest() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), null);
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.of(2021, 11, 10, 14, 15, 0, 0), null);
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.of(2021, 11, 10, 11, 15, 0, 0), null);

        Slice<Asistencia> asistencias = servicioTaller.getAsistenciasOrderByFecha(trabajo.getIdTrabajo(),0,5);
        List<Asistencia> asistenciasLista = asistencias.getContent();
        assertEquals(3, asistenciasLista.size());
        assertEquals(asistencia3.getDescripcion(), asistenciasLista.get(0).getDescripcion());
        assertEquals(asistencia1.getDescripcion(), asistenciasLista.get(1).getDescripcion());
        assertEquals(asistencia2.getDescripcion(), asistenciasLista.get(2).getDescripcion());
    }

    /*** US06  , T6.0*/
    @Test
    public void getElevadoresTest(){
        registrarPuestos();
        List<PuestoTaller> puestos = servicioTaller.getElevadores().getContent();

        assertEquals(5, puestos.size());
        assertEquals(puestos.get(0).getNombre(), "Elevador 1");
        assertEquals(puestos.get(1).getNombre(), "Elevador 2");
        assertEquals(puestos.get(2).getNombre(), "Elevador 3");
        assertEquals(puestos.get(3).getNombre(), "Elevador 4");
        assertEquals(puestos.get(4).getNombre(), "Elevador 5");

    }

    /*** US06, US07  , T7.0*/
    @Test
    public void getHorariosDisponiblesTest(){
        registrarHorarios();
        List<Horarios> horarios = servicioTaller.getHorariosDisponibles();
        assertEquals(20, horarios.size());
        assertEquals(horarios.get(0).getFranjaHoraria(), "8:30-9:00");
        assertEquals(horarios.get(10).getFranjaHoraria(), "15:30-16:00");
        assertEquals(horarios.get(19).getFranjaHoraria(), "20:00-20:30");
    }

    /*** US06  , T8.0*/
    @Test
    public void createAsistenciaTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        AsistenciasDto asistencia = new AsistenciasDto();
        asistencia.setDescripcion("Asistencia 1");
        asistencia.setMatricula("1213HMD");
        asistencia.setDuracionEstimada(20L);
        asistencia.setElevador(puestoTallerDao.findByNombre("Elevador 1").get().getIdPuesto());
        asistencia.setFecha("2021-05-05");
        asistencia.setIdTrabajo(trabajo.getIdTrabajo());
        asistencia.setTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getIdTipo());
        asistencia.setNombreTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getNombre());
        asistencia.setPrecio(20F);
        asistencia.setPeritaje(false);
        ArrayList<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        Usuario usuario = usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get();
        mecanicos.add(new MecanicoAsistenciaDto(usuario.getIdUsuario(), usuario.getNombreUsuario()));
        asistencia.setMecanicos(mecanicos);
        ArrayList<HorariosAsistenciasDto> horarios = new ArrayList<>();
        Horarios horario = horariosDao.findByNombre("8:30-9:00").get();
        horarios.add(new HorariosAsistenciasDto(horario.getIdFranjaHoraria(), horario.getFranjaHoraria()));
        asistencia.setHorasDeTrabajo(horarios);
        Asistencia asistenciaResult = servicioTaller.createAsistencia(asistencia);
        Asistencia asistenciaDao = asistenciasDao.findById(asistenciaResult.getIdAsistencia()).get();

        assertEquals(asistenciaResult.getIdAsistencia(), asistenciaDao.getIdAsistencia());
        assertEquals(asistenciaResult.getDescripcion(), asistenciaDao.getDescripcion());

    }

    /*** US06  , T8.2*/
    @Test
    public void createAsistenciaNotFoundMecanicosTest()  {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        AsistenciasDto asistencia = new AsistenciasDto();
        asistencia.setDescripcion("Asistencia 1");
        asistencia.setMatricula("1213HMD");
        asistencia.setDuracionEstimada(20L);
        asistencia.setElevador(puestoTallerDao.findByNombre("Elevador 1").get().getIdPuesto());
        asistencia.setFecha("2021-05-05");
        asistencia.setIdTrabajo(trabajo.getIdTrabajo());
        asistencia.setTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getIdTipo());
        asistencia.setNombreTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getNombre());
        asistencia.setPrecio(20F);
        asistencia.setPeritaje(false);
        ArrayList<HorariosAsistenciasDto> horarios = new ArrayList<>();
        ArrayList<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        mecanicos.add(new MecanicoAsistenciaDto(1L, "lauri"));
        asistencia.setMecanicos(mecanicos);
        Horarios horario = horariosDao.findByNombre("8:30-9:00").get();
        horarios.add(new HorariosAsistenciasDto(horario.getIdFranjaHoraria(), horario.getFranjaHoraria()));
        asistencia.setHorasDeTrabajo(horarios);

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.createAsistencia(asistencia));

    }

    /*** US06  , T8.3*/
    @Test
    public void createAsistenciaNotFoundTipoTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        AsistenciasDto asistencia = new AsistenciasDto();
        asistencia.setDescripcion("Asistencia 1");
        asistencia.setMatricula("1213HMD");
        asistencia.setDuracionEstimada(20L);
        asistencia.setElevador(puestoTallerDao.findByNombre("Elevador 1").get().getIdPuesto());
        asistencia.setFecha("2021-05-05");
        asistencia.setIdTrabajo(trabajo.getIdTrabajo());
        asistencia.setTipo(54L);
        asistencia.setNombreTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getNombre());
        asistencia.setPrecio(20F);
        asistencia.setPeritaje(false);
        ArrayList<HorariosAsistenciasDto> horarios = new ArrayList<>();
        ArrayList<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        Usuario usuario = usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get();
        mecanicos.add(new MecanicoAsistenciaDto(usuario.getIdUsuario(), usuario.getNombreUsuario()));
        asistencia.setMecanicos(mecanicos);
        Horarios horario = horariosDao.findByNombre("8:30-9:00").get();
        horarios.add(new HorariosAsistenciasDto(horario.getIdFranjaHoraria(), horario.getFranjaHoraria()));
        asistencia.setHorasDeTrabajo(horarios);

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.createAsistencia(asistencia));

    }

    /*** US06  , T8.4*/
    @Test
    public void createAsistenciaNotFoundPuestoTest(){
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        AsistenciasDto asistencia = new AsistenciasDto();
        asistencia.setDescripcion("Asistencia 1");
        asistencia.setMatricula("1213HMD");
        asistencia.setDuracionEstimada(20L);
        asistencia.setElevador(54L);
        asistencia.setFecha("2021-05-05");
        asistencia.setIdTrabajo(trabajo.getIdTrabajo());
        asistencia.setTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getIdTipo());
        asistencia.setNombreTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getNombre());
        asistencia.setPrecio(20F);
        asistencia.setPeritaje(false);
        ArrayList<HorariosAsistenciasDto> horarios = new ArrayList<>();
        ArrayList<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        Usuario usuario = usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get();
        mecanicos.add(new MecanicoAsistenciaDto(usuario.getIdUsuario(), usuario.getNombreUsuario()));
        asistencia.setMecanicos(mecanicos);
        Horarios horario = horariosDao.findByNombre("8:30-9:00").get();
        horarios.add(new HorariosAsistenciasDto(horario.getIdFranjaHoraria(), horario.getFranjaHoraria()));
        asistencia.setHorasDeTrabajo(horarios);

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.createAsistencia(asistencia));

    }

    /*** US06  , T8.5*/
    @Test
    public void createAsistenciaNotFoundTrabajoTest() {
        registrarInfoPrimera();
        AsistenciasDto asistencia = new AsistenciasDto();
        asistencia.setDescripcion("Asistencia 1");
        asistencia.setMatricula("1213HMD");
        asistencia.setDuracionEstimada(20L);
        asistencia.setElevador(puestoTallerDao.findByNombre("Elevador 1").get().getIdPuesto());
        asistencia.setFecha("2021-05-05");
        asistencia.setIdTrabajo(54L);
        asistencia.setTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getIdTipo());
        asistencia.setNombreTipo(tipoAsistenciasDao.findByNombre("Aceite").get().getNombre());
        asistencia.setPrecio(20F);
        asistencia.setPeritaje(false);
        ArrayList<HorariosAsistenciasDto> horarios = new ArrayList<>();
        ArrayList<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        Usuario usuario = usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get();
        mecanicos.add(new MecanicoAsistenciaDto(usuario.getIdUsuario(), usuario.getNombreUsuario()));
        asistencia.setMecanicos(mecanicos);
        Horarios horario = horariosDao.findByNombre("8:30-9:00").get();
        horarios.add(new HorariosAsistenciasDto(horario.getIdFranjaHoraria(), horario.getFranjaHoraria()));
        asistencia.setHorasDeTrabajo(horarios);

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.createAsistencia(asistencia));

    }

    /*** US07 , T9.0*/
    @Test
    public void asignarAsistenciaPuestoTest() throws InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), null);
        asistencia1 = servicioTaller.asignarAsistenciaPuesto(new AsistenciaPuestoTDto(puestoTallerDao.findByNombre("Elevador 2").get().getIdPuesto(), asistencia1.getIdAsistencia()));
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals("Elevador 2", asistenciaFind.getPuesto().getNombre());
    }

    /*** US07 , T9.1*/
    @Test
    public void asignarAsistenciaPuestoNotFoundPuestoTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), null);
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.asignarAsistenciaPuesto(new AsistenciaPuestoTDto(0L, asistencia1.getIdAsistencia())));
    }

    /*** US07 , T9.2*/
    @Test
    public void asignarAsistenciaPuestoNotFoundAsistenciaTest() {
        registrarPuestos();
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.asignarAsistenciaPuesto(new AsistenciaPuestoTDto(puestoTallerDao.findByNombre("Elevador 2").get().getIdPuesto(), 0L)));
    }

    /*** US07  , T9.3*/
    @Test
    public void asignarAsistenciaFranjaHorariaTest() throws InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Horarios hora = horariosDao.findByNombre("11:00-11:30").get();
        AsistenciaFranjaHorariaDto asiHor = new AsistenciaFranjaHorariaDto();
        asiHor.setIdAsistencia(asistencia1.getIdAsistencia());
        Map<Long, String> horarios = new HashMap<>();
        horarios.put(hora.getIdFranjaHoraria(), hora.getFranjaHoraria());
        asiHor.setFranjasHorarias(horarios);
        servicioTaller.asignarAsistenciaFranjaHoraria(asiHor);
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(hora.getFranjaHoraria(), hora.getFranjaHoraria());
    }

    /*** US07  , T9.4*/
    @Test
    public void asignarAsistenciaFranjaHorariaNotFoundTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Horarios hora = horariosDao.findByNombre("11:00-11:30").get();
        AsistenciaFranjaHorariaDto asiHor = new AsistenciaFranjaHorariaDto();
        asiHor.setIdAsistencia(asistencia1.getIdAsistencia());
        Map<Long, String> horarios = new HashMap<>();
        horarios.put(0L, hora.getFranjaHoraria());
        asiHor.setFranjasHorarias(horarios);
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.asignarAsistenciaFranjaHoraria(asiHor));
    }

    /*** US07 , T10.0*/
    @Test
    public void actualizaFechaYHoraAsistenciaTest() throws InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Horarios hora = horariosDao.findByNombre("11:00-11:30").get();
        AsistenciaFranjaHorariaDto asiHor = new AsistenciaFranjaHorariaDto();
        asiHor.setFecha("2021-05-05");
        asiHor.setIdAsistencia(asistencia1.getIdAsistencia());
        Map<Long, String> horarios = new HashMap<>();
        horarios.put(hora.getIdFranjaHoraria(), hora.getFranjaHoraria());
        asiHor.setFranjasHorarias(horarios);
        servicioTaller.actualizaFechaYHoraAsistencia(asiHor);
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(hora.getFranjaHoraria(), hora.getFranjaHoraria());
    }

    /*** US07 , T10.1*/
    @Test
    public void actualizaFechaYHoraAsistenciaNotFoundTest() {
        registrarHorarios();
        Horarios hora = horariosDao.findByNombre("11:00-11:30").get();
        AsistenciaFranjaHorariaDto asiHor = new AsistenciaFranjaHorariaDto();
        asiHor.setFecha("2021-05-05");
        asiHor.setIdAsistencia(0L);
        Map<Long, String> horarios = new HashMap<>();
        horarios.put(hora.getIdFranjaHoraria(), hora.getFranjaHoraria());
        asiHor.setFranjasHorarias(horarios);

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.actualizaFechaYHoraAsistencia(asiHor));
    }

    /*** US09 , T11.0*/
    @Test
    public void getAsistenciaByIDTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = servicioTaller.getAsistenciaByID(asistencia1.getIdAsistencia());

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
    }

    /*** US09 , T11.1*/
    @Test
    public void getAsistenciaByIDNotFoundTest(){
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.getAsistenciaByID(0L));
    }

    /*** US18, US39 , T12.0*/
    @Test
    public void createTrabajoTest() throws InstanceNotFoundException, CamposIntroducidosNoValidosException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        TrabajoDto trabajo = new TrabajoDto("Trabajo 1", "Trabajo Decr 1");
        trabajo.setMatricula(vehiculo.getMatricula());
        trabajo.setPeritado(false);
        Trabajo trabajoResult = servicioTaller.createTrabajo(trabajo);

        assertEquals(trabajo.getNombre(), trabajoResult.getNombre());
        assertEquals(trabajo.getMatricula(), trabajoResult.getVehiculo().getMatricula());
    }

    /*** US18, US39 , T12.1*/
    @Test
    public void createTrabajoNotFoundTest()  {
        registrarInfoPrimera();
        TrabajoDto trabajo = new TrabajoDto("Trabajo 1", "Trabajo Decr 1");
        trabajo.setMatricula("1");
        trabajo.setPeritado(false);

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.createTrabajo(trabajo));
    }

    /*** US18, US39 , T12.2*/
    @Test
    public void createTrabajoCamposInvalidosTest() throws InstanceNotFoundException, CamposIntroducidosNoValidosException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        TrabajoDto trabajo = new TrabajoDto("Trabajo 1", "Trabajo Decr 1");
        trabajo.setMatricula(vehiculo.getMatricula());
        trabajo.setPeritado(true);
        servicioTaller.createTrabajo(trabajo);

        TrabajoDto trabajo1 = new TrabajoDto("Trabajo 1", "Trabajo Decr 1");
        trabajo1.setMatricula(vehiculo.getMatricula());
        trabajo1.setPeritado(true);

        assertThrows(CamposIntroducidosNoValidosException.class, () -> servicioTaller.createTrabajo(trabajo1));
    }

    /*** US23, US30 , T13.0*/
    @Test
    public void getTrabajoByIDTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Trabajo trabajoFind = servicioTaller.getTrabajoByID(trabajo.getIdTrabajo());

        assertEquals(trabajo.getIdTrabajo(), trabajoFind.getIdTrabajo());
        assertEquals(trabajo.getNombre(), trabajoFind.getNombre());
    }

    /*** US23, US30 , T13.1*/
    @Test
    public void getTrabajoByIDNotFoundTest(){
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.getTrabajoByID(0L));
    }

    /*** US24, US31  , T14.0*/
    @Test
    public void cambiarEstadoTrabajoTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajoAbierto = registrarTrabajo(vehiculo, null);
        servicioTaller.cambiarEstadoTrabajo(trabajoAbierto.getIdTrabajo(), "Cerrado");
        Trabajo trabajoCerrado = trabajoDao.findById(trabajoAbierto.getIdTrabajo()).get();

        assertEquals(trabajoAbierto.getIdTrabajo(), trabajoCerrado.getIdTrabajo());
        assertEquals("Cerrado", trabajoCerrado.getEstado().getNombre());
    }

    /*** US24, US31  , T15.1*/
    @Test
    public void cambiarEstadoTrabajoNotFoundTrabajoTest() throws InstanceNotFoundException{
        registrarEstadosTrabajo();
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.cambiarEstadoTrabajo(0L, "Cerrado"));
    }

    /*** US24, US31  , T15.2*/
    @Test
    public void cambiarEstadoTrabajoNotFoundEstadoTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajoAbierto = registrarTrabajo(vehiculo, null);
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.cambiarEstadoTrabajo(trabajoAbierto.getIdTrabajo(), "X"));
    }

    /*** US25 , T15.0*/
    @Test
    public void getTrabajosOrderByFechaTest() {
        registrarInfoPrimera();
        registrarUsuario2();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Vehiculo vehiculo1 = registrarVehiculo2(usuarioDao.findByCorreoElectronicoUsuario("lauri@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, LocalDateTime.of(2021, 11, 10, 17, 15, 0, 0));
        Trabajo trabajo1 = registrarTrabajo(vehiculo1, LocalDateTime.of(2021, 11, 10, 15, 15, 0, 0));

        List<Trabajo> trabajos = servicioTaller.getTrabajosOrderByFecha(0,5).getContent();
        assertEquals(2, trabajos.size());
        assertEquals(trabajo1.getIdTrabajo(), trabajos.get(0).getIdTrabajo());
        assertEquals(trabajo.getIdTrabajo(), trabajos.get(1).getIdTrabajo());
    }

    /*** US29 , T16.0*/
    @Test
    public void getAllPiezasTest(){
        registrarPiezas();
        List<PiezasAsistenciasDto> piezas = servicioTaller.getAllPiezas();

        assertEquals(3, piezas.size());
        assertEquals("Tubo de escape (BMW M Performance)", piezas.get(0).getNombre());
        assertEquals("Electrónica grupo VAG ", piezas.get(2).getNombre());
    }

    /*** US29 , T16.1*/
    @Test
    public void getAllPiezas0sizeTest(){
        List<PiezasAsistenciasDto> piezas = servicioTaller.getAllPiezas();
        assertEquals(0, piezas.size());
    }

    /*** US32 , T17.0*/
    @Test
    public void asignarAsistenciaPiezaTest() throws InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));

        Slice<Pieza> piezas = servicioTaller.getPiezasByAsistencia(asistencia1.getIdAsistencia(), 0, 10);
        List<PiezasAsistenciasDto> listaPiezas =  AsistenciaConversor.toPiezasReparacion(piezas.getContent());
        List<PiezasAsistenciasDto> listaPiezasTransformadas = servicioTaller.getNumeroUnidadesPiezaAsistencia(listaPiezas, asistencia1.getIdAsistencia());

        assertEquals(1, listaPiezasTransformadas.size());
        assertEquals("Electrónica grupo VAG ", listaPiezasTransformadas.get(0).getNombre());
        assertEquals(2, listaPiezasTransformadas.get(0).getNumeroUnidades());
    }

    /*** US32 , T17.1*/
    @Test
    public void asignarAsistenciaPiezaNotFoundPiezaTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), 0L, 2L)));
    }

    /*** US32 , T17.1*/
    @Test
    public void asignarAsistenciaPiezaNotFoundAsistenciaTest() {
        registrarInfoPrimera();
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(0L, piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L)));
    }

    /*** US33 , T18.0*/
    @Test
    public void getPiezasByAsistenciaTest() throws InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Michelin Pilot Sport 4s ").get().getIdPieza(), 5L));

        Slice<Pieza> piezas = servicioTaller.getPiezasByAsistencia(asistencia1.getIdAsistencia(), 0, 10);
        List<PiezasAsistenciasDto> listaPiezas =  AsistenciaConversor.toPiezasReparacion(piezas.getContent());
        List<PiezasAsistenciasDto> listaPiezasTransformadas = servicioTaller.getNumeroUnidadesPiezaAsistencia(listaPiezas, asistencia1.getIdAsistencia());

        assertEquals(2, listaPiezasTransformadas.size());
        assertEquals("Electrónica grupo VAG ", listaPiezasTransformadas.get(0).getNombre());
        assertEquals("Michelin Pilot Sport 4s ", listaPiezasTransformadas.get(1).getNombre());
        assertEquals(2, listaPiezasTransformadas.get(0).getNumeroUnidades());
        assertEquals(5, listaPiezasTransformadas.get(1).getNumeroUnidades());
    }

    /*** US33 , T18.1*/
    @Test
    public void getPiezasByAsistenciaNotFoundTest(){
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.getPiezasByAsistencia(0L, 0,5));
    }

    /*** US34 , T19*/
    @Test
    public void cambiarRetraso() throws InstanceNotFoundException, CampoVacioException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.cambiarRetraso(asistencia1.getIdAsistencia(), "Retraso");
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistenciaFind.getRetrasada(), true);
    }

    /*** US37 , T20.0*/
    @Test
    public void getAsistenciasRetrasadasTest() throws InstanceNotFoundException, CampoVacioException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());

        servicioTaller.cambiarRetraso(asistencia1.getIdAsistencia(), "Retraso");
        servicioTaller.cambiarRetraso(asistencia2.getIdAsistencia(), "Retraso");
        servicioTaller.cambiarRetraso(asistencia3.getIdAsistencia(), "Retraso");
        servicioTaller.cambiarRetraso(asistencia3.getIdAsistencia(), "\"null\"");

        List<Asistencia> asistencias = servicioTaller.getAsistenciasRetrasadas(0, 5).getContent();

        assertEquals(2, asistencias.size());
        assertEquals(asistencia1.getIdAsistencia(), asistencias.get(0).getIdAsistencia());
        assertEquals(asistencia2.getIdAsistencia(), asistencias.get(1).getIdAsistencia());

    }

    /*** US37 , T20.1*/
    @Test
    public void getAsistenciasRetrasadas0SizeTest() throws InstanceNotFoundException, CampoVacioException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());

        servicioTaller.cambiarRetraso(asistencia1.getIdAsistencia(), "Retraso");
        servicioTaller.cambiarRetraso(asistencia1.getIdAsistencia(), "\"null\"");
        servicioTaller.cambiarRetraso(asistencia2.getIdAsistencia(), "Retraso");
        servicioTaller.cambiarRetraso(asistencia2.getIdAsistencia(), "\"null\"");
        servicioTaller.cambiarRetraso(asistencia3.getIdAsistencia(), "Retraso");
        servicioTaller.cambiarRetraso(asistencia3.getIdAsistencia(), "\"null\"");

        List<Asistencia> asistencias = servicioTaller.getAsistenciasRetrasadas(0, 5).getContent();

        assertEquals(0, asistencias.size());
    }

    /*** US38 .T21.0*/
    @Test
    public void getFacturaTest() throws InstanceNotFoundException, StateErrorException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia2.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia3.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));

        servicioTaller.cambiarEstadoTrabajo(trabajo.getIdTrabajo(), "Cerrado");
        assertTrue(servicioTaller.getFactura(trabajo.getIdTrabajo()).contains("Factura"));
    }

    /*** US38 .T21.1*/
    @Test
    public void getFacturaNotFoundTest(){
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.getFactura(0L));
    }

    /*** US38 ,T21.2*/
    @Test
    public void getFacturaStateErrorTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));
        Asistencia asistencia2 = registrarAsistencia("Asistencia 2", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia2.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));
        Asistencia asistencia3 = registrarAsistencia("Asistencia 3", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia3.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));

        assertThrows(StateErrorException.class, () -> servicioTaller.getFactura(trabajo.getIdTrabajo()));
    }

    /*** US33 , T22.0*/
    @Test
    public void getNumeroUnidadesPiezaAsistenciaTest() throws InstanceNotFoundException {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L));
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 5L));
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Michelin Pilot Sport 4s ").get().getIdPieza(), 5L));

        Slice<Pieza> piezas = servicioTaller.getPiezasByAsistencia(asistencia1.getIdAsistencia(), 0, 10);
        List<PiezasAsistenciasDto> listaPiezas =  AsistenciaConversor.toPiezasReparacion(piezas.getContent());
        List<PiezasAsistenciasDto> listaPiezasTransformadas = servicioTaller.getNumeroUnidadesPiezaAsistencia(listaPiezas, asistencia1.getIdAsistencia());

        assertEquals(2, listaPiezasTransformadas.size());
        assertEquals("Electrónica grupo VAG ", listaPiezasTransformadas.get(0).getNombre());
        assertEquals("Michelin Pilot Sport 4s ", listaPiezasTransformadas.get(1).getNombre());
        assertEquals(7, listaPiezasTransformadas.get(0).getNumeroUnidades());
        assertEquals(5, listaPiezasTransformadas.get(1).getNumeroUnidades());

    }

    /*** T23.0*/
    @Test
    public void deleteAsistenciaPiezaTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        servicioTaller.asignarAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 5L));

        List<Pieza> piezas = servicioTaller.getPiezasByAsistencia(asistencia1.getIdAsistencia(), 0,7).getContent();
        AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto = new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 5L);
        servicioTaller.deleteAsistenciaPieza(asistenciaNuevaPiezaDto);

        List<PiezasAsistenciasDto> piezasUnidades = servicioTaller.getNumeroUnidadesPiezaAsistencia(AsistenciaConversor.toPiezasReparacion(piezas), asistencia1.getIdAsistencia());
        assertEquals("Electrónica grupo VAG ",piezasUnidades.get(0).getNombre());
        assertNull(piezasUnidades.get(0).getNumeroUnidades());


    }

    /*** T23.1*/
    @Test
    public void deleteAsistenciaPiezaNotFoundAsistenciaTest(){
        registrarPiezas();
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.deleteAsistenciaPieza(new AsistenciaNuevaPiezaDto(0L, piezaDao.findByNombre("Electrónica grupo VAG ").get().getIdPieza(), 2L)));
    }

    /*** T23.2*/
    @Test
    public void deleteAsistenciaPiezaNotFoundPiezaTest()  {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());

        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.deleteAsistenciaPieza(new AsistenciaNuevaPiezaDto(asistencia1.getIdAsistencia(), 0L, 2L)));
    }

    /*** T24.0*/
    @Test
    public void getHorariosLibresporFechaTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.now(), null);

        ArrayList<List<Horarios>> result = servicioTaller.getHorariosLibresporFecha("2021-12-21");

        assertEquals(19, result.get(0).size());
        assertEquals(20, result.get(1).size());
        assertEquals(20, result.get(2).size());
        assertEquals(20, result.get(3).size());
        assertEquals(20, result.get(4).size());
    }

    /*** T25.0*/
    @Test
    public void crearTipoAsistenciaTest(){
        String nombreTipo = "Tubo de escape";
        String descripcion = "Limpiar tubo de escape";
        servicioTaller.crearTipoAsistencia(nombreTipo, descripcion);
        TipoAsistencias tipo = tipoAsistenciasDao.findByNombre("Tubo de escape").get();

        assertEquals(nombreTipo, tipo.getNombre());
        assertEquals(descripcion, tipo.getDescripcion());
    }

    /*** T26.0*/
    @Test
    public void actualizarAsistenciaTest() throws InstanceNotFoundException{
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaFind.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaFind.getPuesto().getIdPuesto());

        AsistenciasDto asistenciaDto = new AsistenciasDto();
        asistenciaDto.setIdAsistencia(asistencia1.getIdAsistencia());
        asistenciaDto.setFecha("2021-05-05");
        asistenciaDto.setElevador(puestoTallerDao.findByNombre("Elevador 3").get().getIdPuesto());
        asistenciaDto.setPrecio(10F);
        asistenciaDto.setDescripcion("Nueva asistencia actualizada");
        asistenciaDto.setTipo(asistencia1.getTipo().getIdTipo());
        asistenciaDto.setDuracionEstimada(asistencia1.getDuracionEstimada());
        asistenciaDto.setMecanicos(new ArrayList<>());
        asistenciaDto.setHorasDeTrabajo(new ArrayList<>());
        asistenciaDto.setIdTrabajo(asistencia1.getTrabajo().getIdTrabajo());
        servicioTaller.actualizarAsistencia(asistenciaDto, asistencia1.getIdAsistencia());

        asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaDto.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaDto.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaDto.getElevador());
        assertEquals(asistencia1.getFecha().toString(), "2021-05-05T00:00");

    }

    /*** T26.1*/
    @Test
    public void actualizarAsistenciaNotFoundUserTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaFind.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaFind.getPuesto().getIdPuesto());

        AsistenciasDto asistenciaDto = new AsistenciasDto();
        asistenciaDto.setIdAsistencia(asistencia1.getIdAsistencia());
        asistenciaDto.setFecha("2021-05-05");
        asistenciaDto.setElevador(puestoTallerDao.findByNombre("Elevador 3").get().getIdPuesto());
        asistenciaDto.setPrecio(10F);
        asistenciaDto.setDescripcion("Nueva asistencia actualizada");
        asistenciaDto.setTipo(asistencia1.getTipo().getIdTipo());
        asistenciaDto.setDuracionEstimada(asistencia1.getDuracionEstimada());
        List<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        mecanicos.add(new MecanicoAsistenciaDto(0L, "Pepe"));
        asistenciaDto.setMecanicos(mecanicos);
        asistenciaDto.setHorasDeTrabajo(new ArrayList<>());
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.actualizarAsistencia(asistenciaDto, asistencia1.getIdAsistencia()));
    }

    /*** T26.2*/
    @Test
    public void actualizarAsistenciaNotFoundTATest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaFind.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaFind.getPuesto().getIdPuesto());

        AsistenciasDto asistenciaDto = new AsistenciasDto();
        asistenciaDto.setIdAsistencia(asistencia1.getIdAsistencia());
        asistenciaDto.setFecha("2021-05-05");
        asistenciaDto.setElevador(puestoTallerDao.findByNombre("Elevador 3").get().getIdPuesto());
        asistenciaDto.setPrecio(10F);
        asistenciaDto.setDescripcion("Nueva asistencia actualizada");
        asistenciaDto.setTipo(0L);
        asistenciaDto.setDuracionEstimada(asistencia1.getDuracionEstimada());
        asistenciaDto.setMecanicos(new ArrayList<>());
        asistenciaDto.setHorasDeTrabajo(new ArrayList<>());
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.actualizarAsistencia(asistenciaDto, asistencia1.getIdAsistencia()));
    }

    /*** T26.3*/
    @Test
    public void actualizarAsistenciaNotFoundPuestoTest()  {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaFind.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaFind.getPuesto().getIdPuesto());

        AsistenciasDto asistenciaDto = new AsistenciasDto();
        asistenciaDto.setIdAsistencia(asistencia1.getIdAsistencia());
        asistenciaDto.setFecha("2021-05-05");
        asistenciaDto.setElevador(0L);
        asistenciaDto.setPrecio(10F);
        asistenciaDto.setDescripcion("Nueva asistencia actualizada");
        asistenciaDto.setTipo(asistencia1.getTipo().getIdTipo());
        asistenciaDto.setDuracionEstimada(asistencia1.getDuracionEstimada());
        asistenciaDto.setMecanicos(new ArrayList<>());
        asistenciaDto.setHorasDeTrabajo(new ArrayList<>());
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.actualizarAsistencia(asistenciaDto, asistencia1.getIdAsistencia()));
    }

    /*** T26.4*/
    @Test
    public void actualizarAsistenciaNotFoundTrabajoTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaFind.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaFind.getPuesto().getIdPuesto());

        AsistenciasDto asistenciaDto = new AsistenciasDto();
        asistenciaDto.setIdAsistencia(asistencia1.getIdAsistencia());
        asistenciaDto.setFecha("2021-05-05");
        asistenciaDto.setElevador(asistencia1.getPuesto().getIdPuesto());
        asistenciaDto.setPrecio(10F);
        asistenciaDto.setDescripcion("Nueva asistencia actualizada");
        asistenciaDto.setTipo(asistencia1.getTipo().getIdTipo());
        asistenciaDto.setDuracionEstimada(asistencia1.getDuracionEstimada());
        asistenciaDto.setIdTrabajo(0L);
        asistenciaDto.setMecanicos(new ArrayList<>());
        asistenciaDto.setHorasDeTrabajo(new ArrayList<>());
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.actualizarAsistencia(asistenciaDto, asistencia1.getIdAsistencia()));
    }

    /*** T26.5*/
    @Test
    public void actualizarAsistenciaNotFoundAsistenciaTest() {
        registrarInfoPrimera();
        Vehiculo vehiculo = registrarVehiculo(usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get(), modeloDao.findByNombre("Ford focus").get());
        Trabajo trabajo = registrarTrabajo(vehiculo, null);
        Asistencia asistencia1 = registrarAsistencia("Asistencia 1", trabajo, LocalDateTime.of(2021, 11, 10, 13, 15, 0, 0), usuarioDao.findByCorreoElectronicoUsuario("laura@gmail.com").get());
        Asistencia asistenciaFind = asistenciasDao.findById(asistencia1.getIdAsistencia()).get();

        assertEquals(asistencia1.getIdAsistencia(), asistenciaFind.getIdAsistencia());
        assertEquals(asistencia1.getDescripcion(), asistenciaFind.getDescripcion());
        assertEquals(asistencia1.getPrecio(), asistenciaFind.getPrecio());
        assertEquals(asistencia1.getDuracionEstimada(), asistenciaFind.getDuracionEstimada());
        assertEquals(asistencia1.getTipo().getIdTipo(), asistenciaFind.getTipo().getIdTipo());
        assertEquals(asistencia1.getPuesto().getIdPuesto(), asistenciaFind.getPuesto().getIdPuesto());

        AsistenciasDto asistenciaDto = new AsistenciasDto();
        asistenciaDto.setIdAsistencia(0L);
        asistenciaDto.setFecha("2021-05-05");
        asistenciaDto.setElevador(asistencia1.getPuesto().getIdPuesto());
        asistenciaDto.setPrecio(10F);
        asistenciaDto.setDescripcion("Nueva asistencia actualizada");
        asistenciaDto.setTipo(asistencia1.getTipo().getIdTipo());
        asistenciaDto.setDuracionEstimada(asistencia1.getDuracionEstimada());
        asistenciaDto.setIdTrabajo(asistencia1.getTrabajo().getIdTrabajo());
        asistenciaDto.setMecanicos(new ArrayList<>());
        asistenciaDto.setHorasDeTrabajo(new ArrayList<>());
        assertThrows(InstanceNotFoundException.class, () -> servicioTaller.actualizarAsistencia(asistenciaDto, 0L));
    }

}
