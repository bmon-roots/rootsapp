package ar.edu.ort.bmon.rootsapp.constants;

public class Constants {
    public static final String GOOGLE_ACCOUNT = "google_account";
    ///Firebase DB Collection Selectors
    public static final String PLANT_COLLECTION  = "plants";
    public static final String SPECIES_COLLECTION = "species";
    public static final String MATERIAL_COLLECTION = "materials";
    //Firebase Plant Fields
    public static final String PLANT_IMAGE_URI = "imageUri";
    //Firebase Storage Collection Selectors
    public static final String PLANT_IMAGES_FOLDER = "/images/plants";
    //Opciones del menu Crear una nueva entrada
    public static final String ADD_NEW_ENTRY_TITLE = "Crear Nueva Entrada";
    public static final String ADD_NEW_PLANT = "Nueva Planta";
    public static final String ADD_NEW_SPECIES = "Nueva Especie";
    //Constantes del ProgressDialog
    public static final String UPLOADING_PHOTO = "Subiendo foto";
    public static final String PERCENTAGE_COMPLETE = " % Completado";
    //Opciones del menu Cambiar Foto
    public static final String CHANGE_PHOTO_MENU_TITLE = "Cambiar Foto";
    public static final String SELECT_FROM_GALLERY = "Seleccionar de la galeria";
    public static final String TAKE_PHOTO = "Tomar una foto";
    //Botones de los menus
    public static final String CANCEL_BUTTON = "Cancelar";
    public static final String ACCEPT_BUTTON = "Aceptar";
    //Identificadores de Intents de Galeria y Camara
    public static final int REQUEST_IMAGE_CAPTURE = 100;
    public static final int GALLERY_REQUEST = 101;
    //Mensajes de la aplicacion
    public static final String PLANT_CREATE_SUCCESS = "Se agrego la planta exitosamente";
    public static final String PLANT_CREATE_ERROR = "Hubo un error al crear la planta, intente nuevamente";
    public static final String IMAGE_GALLERY_ERROR = "Hubo un error al seleccionar la foto";
    public static final String FIREBASE_STORAGE_ERROR = "Hubo un error al subir la foto. Por favor, intente nuevamente";
    //Error Tags
    public static final String FIREBASE_ERROR = "Firebase_Error";
    public static final String STORAGE_ERROR = "Storage Error";
    //Detalle de planta. Agregar Tareas
    public static final String ADD_NEW_TASK_TITLE = "Crear Nueva Tarea";
    public static final String ADD_TASK_FUMIGATE = "Fumigar";
    public static final String ADD_TASK_PRUNE = "Podar";
    public static final String ADD_TASK_FERTILIZE = "Fertilizar";
}
