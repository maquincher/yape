package com.maquincher.yaperecipes.mockdata

import com.google.gson.Gson
import com.maquincher.yaperecipes.models.Recipe

val recipesData:List<Recipe> =
    Gson().fromJson(
        """[{
"id":1,
"name":"primera",
"latlng": {"lat":-12.15317863007613, "lng":-76.97406979042594},
"description":"Descripcion de la primera receta",
"ingredients":["azucar","salsa de tomate","pimienta", "harina","melon","palta","maiz","pescado","pera","arroz","miel","sirup","ajonjoli"],
"img_src":"https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=80&resize=200,200"
},
{
"id":2,
"name":"segunda",
"latlng": {"lat":-12.172218387823465, "lng":-76.9928014385624},
"description":"Descripcion de la segunda receta",
"ingredients":["salsa de tomate", "harina","palta","pescado","arroz","sirup","cebolla"],
"img_src":"https://www.hogarmania.com/archivos/201604/333-2-brownie-chocolate-eva-arguinano-xl-668x400x80xX.jpg"
},
{
"id":3,
"name":"tercera",
"latlng": {"lat":-12.143053150130468, "lng":-77.00267895740271},
"description":"Descripcion de la tercera receta",
"ingredients":["azucar","pimienta", "harina","palta","pescado","arroz","manzana","sirup","ajonjoli"],
"img_src":"https://www.hogarmania.com/archivos/201604/333-2-brownie-chocolate-eva-arguinano-xl-668x400x80xX.jpg"
},{
"id":4,
"name":"cuarta",
"latlng": {"lat":-12.162532005342943, "lng":-76.96758187635085},
"description":"Descripcion de la cuarta receta",
"ingredients":["azucar","salsa de tomate","pimienta", "harina"],
"img_src":"https://www.hogarmania.com/archivos/201604/333-2-brownie-chocolate-eva-arguinano-xl-668x400x80xX.jpg"
},
{
"id":5,
"name":"quinta",
"latlng": {"lat":-12.136386771083442, "lng":-77.01720732860203},
"description":"Descripcion de la quinta receta",
"ingredients":["melon","palta","maiz","pescado","pera","arroz","miel","sirup","ajonjoli"],
"img_src":"https://www.hogarmania.com/archivos/201604/333-2-brownie-chocolate-eva-arguinano-xl-668x400x80xX.jpg"
},
{
"id":6,
"name":"sexta",
"latlng": {"lat":-12.167179777969231, "lng":-77.01483006142831},
"description":"Descripcion de la sexta receta",
"ingredients":["stevia","mayonesa","pimienta negra", "pan","melocoton","aguacate","mazorca","salmon","pera","arroz","dulce de leche"],
"img_src":"https://www.hogarmania.com/archivos/201604/333-2-brownie-chocolate-eva-arguinano-xl-668x400x80xX.jpg"
}

]""", Array<Recipe>::class.java
    ).toList()


object  LocalMockData {

    fun getRecipes(): List<Recipe> {
        return recipesData
    }
}