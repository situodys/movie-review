import {Paper, Stack} from "@mui/material";
import Image from "./Image";

function UploadedImages(props) {
    return (
        <Paper style={{background: "aliceblue",
        height: 300}}>
            <Stack direction={"row"} spacing={2} justifyContent={"center"} alignContent={"center"} overflow={"auto"}>
                {props.images && props.images.map((item, idx) => (
                        <Image url={item.thumbnailURL}/>
                    )
                )}
            </Stack>
        </Paper>
    )
}

export default UploadedImages;
