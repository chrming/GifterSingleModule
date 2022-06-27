package com.example.gifter_single_module.profile.profile_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gifter_single_module.profile.model.Profile

@Preview
@Composable
fun PreviewProfileItem() {
    val profile = Profile(
        name = "Adam Małysz",
        birthdayDate = 10102000,
        namedayDate = 1010
    )
    ProfileItem(profile = profile, onClick = {}, onDeleteClick = {})

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileItem(
    profile: Profile,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp),
        elevation = 10.dp,
        border = BorderStroke(1.dp, SolidColor(Color.Black)),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                        .width(IntrinsicSize.Min),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = profile.birthdayDate.toString(),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = modifier.height(4.dp))
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                {
                    // Place for a picture
                    Image(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(85.dp),
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Profile's image"
                    )
                }
                Column {
                    IconButton(
                        onClick = onDeleteClick,

                        ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Profile",
                        )
                    }
                }
            }
        }
    }
}
