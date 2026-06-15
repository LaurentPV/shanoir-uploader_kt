package org.example.front_end

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.front_end.common_elements.icons.check_box
import org.example.front_end.common_elements.icons.check_box_outline_blank
import org.example.front_end.common_elements.icons.close
import org.example.front_end.viewmodel.ViewModelShUp

@Composable
fun ExportToServerWindow(viewModel: ViewModelShUp, onNavBarSwitch: () -> Unit) {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            var selectedLines by remember { mutableStateOf("") }

            /**
             * NAV BAR
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray)
            ){
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                onNavBarSwitch()
                            }
                        )
                ){
                    Text(
                        text = "Préparation locale des données",
                        modifier = Modifier
                            .padding(30.dp,15.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = {}
                        )
                        .background(Color(0xEA,0xDD,0xFF))
                        .drawBehind{ // Draw a purple line at the bottom of the Row
                            val bordersize = 4.dp.toPx()
                            drawLine(
                                color = Color(0x67,0x50,0xA4),
                                start = Offset(0f, size.height-2f),
                                end = Offset(size.width, size.height-2f),
                                strokeWidth = bordersize
                            )
                        }
                ) {
                    Text(
                        text = "Exporter vers le serveur (Shanoir)",
                        modifier = Modifier
                            .padding(30.dp,15.dp)
                    )
                }
            }


            /**
             * WINDOW CONTENT
             */
            var columnScrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.726f)
                    .padding(20.dp)
                    //.verticalScroll(columnScrollState)
                    .weight(1f,false),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //var lineSelected by remember {mutableStateOf(mutableListOf())}

                /**
                 * Imported Data Tab
                 */
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f,false),
                ){
                    val testData = viewModel.testData
                    TableScreen(tableData = testData,
                        onSelected = { data : List<String> ->
                            viewModel.addLine(data)
                            println("Nb Lignes selectionnées : ${viewModel.selectedLines.size}")
                        },
                        onUnselected = { data ->
                            viewModel.removeLine(data)
                            println(viewModel.getNbSelectedLines())
                        }
                    )
                }
            }
        }
    }
}

// Source - https://stackoverflow.com/a/68143597
// Posted by nglauber
// Retrieved 2026-06-05, License - CC BY-SA 4.0

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    align: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp),
        textAlign = align
    )
}

@Composable
fun RowScope.TableCellIcon(
    icon: ImageVector,
    contentDescription: String = "",
    weight: Float,
    onIconClick: () -> Unit
) {
       Icon(
           imageVector = icon,
           contentDescription = contentDescription,
           modifier = Modifier
               .border(1.dp, Color.Black)
               .weight(weight)
               .clickable(
                   onClick = onIconClick
               )
               .padding(vertical = 8.dp)
       )
}

@Composable
fun RowScope.TableCellState(
    state: String,
    weight: Float,
) {
    var backgroundColor = Color.White

    when(state) {
        "FINISHED" -> backgroundColor = Color.LightGray
        "ERROR" -> backgroundColor = Color(0xCF,0x00,0x00)
        "READY" -> backgroundColor = Color(0x29,0xCF,0x00)
        "CHECK_OK" -> backgroundColor = Color(0x5B,0xA2,0xFF)
        "CHECK_KO" -> backgroundColor = Color(0xFF,0x78,0x02)
    }

    Text(
        text = state,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .background(backgroundColor)
            .padding(8.dp),
    )
}

@Composable
fun TableScreen(tableData: List<List<String>> = mutableListOf(), onSelected: (List<String>) -> Unit, onUnselected: (List<String>) -> Unit) {
    // Each cell of a column must have the same weight.
    val iconColumnWeight = .025f // 5%
    val baseColumnWeight = .1f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        ) {

        // Here is the header
        item {
            Row(Modifier.background(Color(0x89,0x76,0xBC)).border(1.dp, Color.Black)) {
                Box(modifier = Modifier.width(72.dp).border(1.dp,Color.Black))
                TableCell(text = "ID", weight = baseColumnWeight, align = TextAlign.Center)
                TableCell(text = "Nom du Patient", weight = baseColumnWeight, align = TextAlign.Center)
                TableCell(text = "IPP", weight = baseColumnWeight, align = TextAlign.Center)
                TableCell(text = "Date de l'étude", weight = baseColumnWeight, align = TextAlign.Center)
                TableCell(text = "IRM", weight = baseColumnWeight, align = TextAlign.Center)
                TableCell(text = "Etat", weight = baseColumnWeight, align = TextAlign.Center)
                TableCellIcon(close,"",iconColumnWeight,{})
            }
        }
        // Here are all the lines of your table.
        items(tableData) { lineData ->
            var iconCheckbox by remember { mutableStateOf(check_box_outline_blank) }
            var backroundColor by remember{ mutableStateOf(Color.White) }
            var isRowSelected by remember { mutableStateOf(false) }

            if (isRowSelected) {
                iconCheckbox = check_box
                backroundColor = Color(0xEA,0xDD,0xFF)
                onSelected(lineData)
            } else {
                iconCheckbox = check_box_outline_blank
                backroundColor = Color.White
                onUnselected(lineData)
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backroundColor)
            ) {
                TableCellIcon(iconCheckbox, weight = iconColumnWeight, onIconClick = {isRowSelected = !isRowSelected}) // Checkbox
                TableCell(lineData[0], weight = baseColumnWeight) // ID
                TableCell(lineData[1], weight = baseColumnWeight) // Name
                TableCell(lineData[2], weight = baseColumnWeight) // IPP
                TableCell(lineData[3], weight = baseColumnWeight) // Study Date
                TableCell(lineData[4], weight = baseColumnWeight) // IRM
                TableCellState(lineData[5], weight = baseColumnWeight) // State
                TableCellIcon(close, weight = iconColumnWeight, onIconClick = {}) // Delete line
            }
        }
    }
}