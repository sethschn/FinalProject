import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/app/models/resource';
import { ResourceService } from 'src/app/services/resource.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-resource-detail',
  templateUrl: './resource-detail.component.html',
  styleUrls: ['./resource-detail.component.css']
})
export class ResourceDetailComponent implements OnInit {

  selected = null;
  newResource = new Resource();
  editResource = null;
  resourceList: Resource[] = [];

  constructor(private resourceSvc: ResourceService, private currentRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.resourceSvc.showResource(this.currentRoute.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.selected = data;
        },
        bad => {
          this.router.navigateByUrl('resourceDetail')
          console.error('ResourceDetailComponent.reload(): error retrieving resource list');
          console.error(bad);
        }
        );
      }
      this.loadResources();
  }

  displayResource(resource){
    this.router.navigateByUrl(`/resources/${resource.id}`);
 }

 showResource(id){
  this.resourceSvc.showResource((this.currentRoute.snapshot.paramMap.get('id'))).subscribe(
    good => {
      this.loadResources();
    },
    bad => {
      console.error("Admin.show(): error")
    }
  )
};

displayTable(){
  this.selected = null;
}

loadResources(){
  this.resourceSvc.indexResource().subscribe(
    data => {this.resourceList = data;
    },
    bad => {
      console.error("EventDetailComponent.loadEvents(): error loading");
      console.error(bad);
    }
  );
}

createResource(resource: Resource){
  console.log(resource);
  this.resourceSvc.createResource(resource).subscribe(
    good => {
      this.loadResources();
      this.newResource = new Resource();
    },
    bad => {
      console.error("AdminComponent.createResource(): error adding");
      console.error(bad);
    })
}

setEditResource(){
  this.editResource = Object.assign({}, this.selected)
}

updateResource(resource: Resource){
  console.log(resource);
 this.resourceSvc.updateResource(resource).subscribe(
   good =>{
     this.loadResources();
     this.editResource= null;
     this.selected = this.editResource;
   },
   bad => {
     console.error("AdminComponent.updateResource(): error updating");
     console.error(bad);
   })
}

deleteResource(resourceId){
  this.resourceSvc.deleteResource(resourceId).subscribe(

    data => {
      this.loadResources();
      this.selected = null;

    },
    err => {
      console.error("EventDetailListComponent.deleteEvent(): error deleting" + err);
    })
  };

}
