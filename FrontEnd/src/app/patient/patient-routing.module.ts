import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AverageDataComponent } from './average-data/average-data.component';
import { ChartsDataComponent } from './charts-data/charts-data.component';
import { InsertDataComponent } from './insert-data/insert-data.component';
import { ListDataComponent } from './list-data/list-data.component';
import { UpdateDataComponent } from './update-data/update-data.component';


const routes: Routes = 
[
{path: 'insertdata',  component: InsertDataComponent},
{path: 'listdata',  component: ListDataComponent},
{path: 'updatedata',  component: UpdateDataComponent},
{path: 'averagedata',  component: AverageDataComponent},
{path: 'chartsdata',  component: ChartsDataComponent},

];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
