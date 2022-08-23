import {AppBar, Button, Grid, Toolbar, Typography} from "@mui/material";

const NavigationBar = ()=> {
    return(
        <AppBar position={"static"}>
            <Toolbar>
                <Grid justifyContent={"space-between"} container>
                    <Grid item>
                        <Typography variant={"h6"}>오늘의 할일</Typography>
                    </Grid>
                    <Grid>
                        <Button color={"inherit"} >
                            로그아웃
                        </Button>
                    </Grid>
                </Grid>
            </Toolbar>
        </AppBar>
    )
}
export default NavigationBar;