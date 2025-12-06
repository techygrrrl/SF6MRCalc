package stream.techygrrrl.sf6mrcalc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import stream.techygrrrl.sf6mrcalc.R
import stream.techygrrrl.sf6mrcalc.ui.PreviewTheme

@Composable
fun MasterRankImage(
    mr: Int,
    modifier: Modifier = Modifier
) {
    val drawableResId = rememberSaveable<Int>(mr) {
        if (mr >= 1800) return@rememberSaveable R.drawable.master_3
        if (mr >= 1700) return@rememberSaveable R.drawable.master_2
        if (mr >= 1600) return@rememberSaveable R.drawable.master_1

        return@rememberSaveable R.drawable.master_0
    }

    val contentDescriptionResId = rememberSaveable<Int>(mr) {
        if (mr >= 1800) return@rememberSaveable R.string.master_rank_image_content_description_ultimate_master
        if (mr >= 1700) return@rememberSaveable R.string.master_rank_image_content_description_grand_master
        if (mr >= 1600) return@rememberSaveable R.string.master_rank_image_content_description_high_master

        return@rememberSaveable R.string.master_rank_image_content_description_master
    }

    Image(
        painter = painterResource(drawableResId),
        contentDescription = stringResource(contentDescriptionResId),
        modifier = modifier,
    )
}

private class MasterRankImagePreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = sequenceOf(
            1500,
            1600,
            1700,
            1800,
        )

}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MasterRankImagePreviewParameterProvider::class) mr: Int,
) {
    PreviewTheme {
        MasterRankImage(mr = mr)
    }
}
