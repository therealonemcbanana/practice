export interface Aviary {
  id: number;
  size: number;
  state: string;
}

export interface Species {
  id: number;
  title: string;
  description?: string;
}

export interface Food {
  id: number;
  name: string;
  amount: number;
}

export interface Employee {
  id: number;
  name: string;
  salary: number;
}

export interface Animal {
  id: number;
  name: string;
  gender: string;
  age: number;
  aviary: Aviary;
  species: Species;
  foodSet: Food[];
  employees: Employee[];
}
  