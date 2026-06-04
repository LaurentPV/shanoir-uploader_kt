package org.example.front_end.common_elements.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImportTypeBar(activeImportType: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind{
                val bordersize = 1.dp.toPx()
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, size.height-2f),
                    end = Offset(size.width, size.height-2f),
                    strokeWidth = bordersize
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        when(activeImportType) {
            "PACS" -> {
                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = {}
                        )
                        .background(Color(0xEA,0xDD,0xFF))
                        .drawBehind{
                            val bordersize = 4.dp.toPx()
                            drawLine(
                                color = Color(0x67,0x50,0xA4),
                                start = Offset(0f, size.height-2f),
                                end = Offset(size.width, size.height-2f),
                                strokeWidth = bordersize
                            )
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Requêter le PACS",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(67.dp, 20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = {

                            }
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Ajouter depuis le Disk",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(67.dp, 20.dp)
                    )
                }
            }

            "Disk" -> {
                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = {}
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Requêter le PACS",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(67.dp, 20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = {}
                        )
                        .background(Color(0xEA,0xDD,0xFF))
                        .drawBehind{
                            val bordersize = 4.dp.toPx()
                            drawLine(
                                color = Color(0x67,0x50,0xA4),
                                start = Offset(0f, size.height-2f),
                                end = Offset(size.width, size.height-2f),
                                strokeWidth = bordersize
                            )
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Ajouter depuis le Disk",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(67.dp, 20.dp)
                    )
                }
            }
        }



    }

}