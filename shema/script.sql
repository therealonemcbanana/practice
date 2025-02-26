CREATE TABLE IF NOT EXISTS public.aviary
(
    id SERIAL PRIMARY KEY,
    size INTEGER CHECK (size > 0) NOT NULL,
    state TEXT
);

CREATE TABLE IF NOT EXISTS public.food
(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    amount INTEGER CHECK (amount >= 0) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.species
(
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS public.animal
(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    gender TEXT NOT NULL,
    age INTEGER CHECK (age > 0) NOT NULL,
    aviary_id INTEGER NOT NULL,
    species_id INTEGER NOT NULL,
    FOREIGN KEY (aviary_id) REFERENCES public.aviary(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (species_id) REFERENCES public.species(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS public.employee
(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    salary INTEGER CHECK (salary >= 0) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.feeding
(
    food_id INTEGER NOT NULL,
    animal_id INTEGER NOT NULL,
    PRIMARY KEY (food_id, animal_id),
    FOREIGN KEY (food_id) REFERENCES public.food(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (animal_id) REFERENCES public.animal(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS public.care
(
    animal_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    PRIMARY KEY (animal_id, employee_id),
    FOREIGN KEY (employee_id) REFERENCES public.employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (animal_id) REFERENCES public.animal(id) ON DELETE CASCADE ON UPDATE CASCADE
);