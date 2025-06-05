Body de resolvers a probar

mutation CrearPaciente{
  createPaciente(input: { name: "Edwar Fonseca", email: "edwarfonsek0028@gmail.com" }) {
    id
    name
    email
  }
}

query ObtenerPacientes{
  getPacientes {
    id
    name
    email
  }
}

query ObtenerPacientePorId {
  getPacienteById(id: "0c79ebc3-637d-49d2-883c-c7a00ec02676") {
    id
    name
    email
  }
}

mutation ActualizarPaciente  {
  updatePaciente(
    id: "6fb055c0-614a-498e-bda9-9092c1a19507"
    input: {
      name: "Juan Pérez García E"
      email: "juan.perez.nuevo@email.com"
    }
  ) {
    id
    name
    email
  }
}

mutation EliminarPaciente {
  deletePaciente(id: "6fb055c0-614a-498e-bda9-9092c1a19507")
}