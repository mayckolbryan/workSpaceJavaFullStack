//comparar un resultado de futbol y decir quien es el ganador
let equipoA = "Barcelona";
let equipoB = "Real Madrid";

let resultadoA = -2;
let resultadoB = -3;

if (resultadoA >= 0 && resultadoB >= 0) {
    if (resultadoA > resultadoB) {
        console.log("Ganó " + equipoA);
    } else if (resultadoA === resultadoB) {
        console.log("Empataron");
    }
    else {
        console.log("Ganó " + equipoB);
    }
}else{
    console.log("numeros no permitdos");
}
