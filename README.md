# XQuery:

1. (1 punto) Devuelve la frase "[nombre] ha ganado el premio de [categoria] en el año [año]"

//En esta parte, declaramos la variable premio y devolvemos una concatenación de los contenidos de premiado, categoría y año

for $premio in doc('premios.xml')/premios_nobel/premios/premio
return concat($premio/premiado/text(), ' ha ganado el premio de ', $premio/@categoria, ' en el año ', $premio/año/text())

2.- (1 punto) Una tabla html [categoria] | [premiado] ordenada de mayor a menor por los [años]

//Aquí realizamos lo mismo, solo que en formato tabla, y además ordenadode mayor a menor (utilizando order by $premio/año descending permitimos al programa clasificarlos por orden descendente

<table>
  <tr>
    <th>Categoría</th>
    <th>Premiado</th>
    <th>Años</th>
  </tr>
  {
    for $premio in doc('premios.xml')/premios_nobel/premios/premio
    order by $premio/año descending
    return
    <tr>
      <td>{data($premio/@categoria)}</td>
      <td>{data($premio/premiado)}</td>
      <td>{data($premio/año)}</td>
    </tr>
  }
</table>

//Aquí, incluimos un nuevo premiado. Para ello, declaramos una variable nuevo_premio, donde introducimos los datos necesarios. Una vez hecho esto, hacemos un return insert node con los datos.

3.- (2 punto) Incluir un nuevo premiado en un nuevo fichero

for $premio in doc('premios.xml')/premios_nobel/premios
let $nuevo_premio :=
<premio categoria = "[física]">
<año>[1986]</año>
<premiado>[Heinrich Rohrer]</premiado>
<motivo>[Por su descubrimiento del microscopio del efecto túnel]</motivo>
</premio>
return insert node($nuevo_premio,$premio) into $premio

//Por último, incluímos un motivo vacío (con not especificamos que todos aquellos nodos que no tengan motivo, se les incluya uno.

4.- (2 puntos) Realizar un fichero nuevo incluyendo motivos en los que no tienen

for $premio in doc('premios.xml')/premios_nobel/premios/premio[not(motivo)]
let $motivo := "Insertar motivo aquí"
return insert node <motivo>$motivo</motivo> into $premio
